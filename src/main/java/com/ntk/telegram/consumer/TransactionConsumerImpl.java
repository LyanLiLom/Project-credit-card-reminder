package com.ntk.telegram.consumer;

import com.ntk.model.CreditCardOwnerModel;
import com.ntk.model.UserModel;
import com.ntk.repository.MongoCreditCardOwnerRepository;
import com.ntk.repository.TransactionRepository;
import com.ntk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ntk.constant.TelegramConstant;
import com.ntk.model.TransactionModel;
import com.ntk.telegram.TransactionConsumer;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TransactionConsumerImpl implements TransactionConsumer{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private MongoCreditCardOwnerRepository creditCardOwnerRepository;

    private Map<Long, String> registeringUsers = new HashMap<>();
    private Map<String, String> userData = new HashMap<>();

    @Override
    public SendMessage handleConsumer(Update update) {
        Long chatId = update.getMessage().getChatId();
        if (registeringUsers.containsKey(chatId)) {
            return handleRegistrationInput(chatId, update.getMessage().getText());
        }
        String[] command = update.getMessage().getText().split(" ");
        return  executeCommand(update, command[0]);
    }

    public SendMessage executeCommand(Update update, String command) {
        switch (command) {
            case TelegramConstant.CHECK_TRANSACTION_COMMAND:
                return getTransactionCommand(update);
            case TelegramConstant.INSERT_TRANSACTION_COMMAND:
                return insertTransactionCommand(update);
            case TelegramConstant.REGISTER_TRANSACTION_COMMAND:
                return registerTransactionCommand(update);
            default:
                return null;
        }
    }



    public SendMessage getTransactionCommand(Update update) {
        SendMessage message = null;
        log.info("===================================================================");
        log.info("The username sent: {}", update.getMessage().getChat().getUserName());
        // Set variables
        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        log.info("ChatId: ", update.getMessage().getChatId());

        message = SendMessage // Create a message object
            .builder()
            .chatId(chat_id)
            .text(message_text + " " + Long.toString(chat_id) + " get transaction coommand success")
            .build();
        return message;
    }

    public SendMessage insertTransactionCommand(Update update) {
        SendMessage message = null;
        log.info("===================================================================");
        log.info("The username sent: {}", update.getMessage().getChat().getUserName());
        // Set variables
        String messageText = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        String username = update.getMessage().getChat().getUserName();

        TransactionModel transaction = TransactionModel.builder()
                .creditCardNumber("123456")
                .owerName("Kieu Vy")
                .userName(username)
                .chatId(chat_id)
                .amount(3000L)
                .transactionDate(LocalDateTime.now())
                .creditCardType("VISA")
                .cutOffDate(LocalDateTime.now())
                .build();

        CreditCardOwnerModel creditCardOwnerModel = CreditCardOwnerModel.builder()
                .cardNumber("1")
                .creditCardTypeId(1L)
                .id(1L)
                .ownerName("abc")
                .expiredDate(LocalDateTime.now())
                .phoneNumber("123")
                .build();
        creditCardOwnerRepository.save(creditCardOwnerModel);

        if (transactionRepository != null) {
            transactionRepository.save(transaction);
            System.out.println(transaction);
            log.info("Transaction saved successfully.");
        } else {
            log.error("TransactionRepository is null. Cannot save transaction.");
        }

        message = SendMessage // Create a message object
                .builder()
                .chatId(chat_id)
                .text(messageText + " " + Long.toString(chat_id) + " get transaction coommand success")
                .text("Transaction saved successfully for user: " + username)
                .build();
        return message;
    }


    private SendMessage registerTransactionCommand(Update update) {
        SendMessage message = null;

        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println("Step 1");
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (!registeringUsers.containsKey(chatId)) {
                System.out.println("step 2");

                registeringUsers.put(chatId, "waitingForName");
                message = createResponseMessage(chatId, "Nhập tên của bạn: ");
                System.out.println("Current registeringUsers: " + registeringUsers);
            } else {
                System.out.println("step 3");
                message = handleRegistrationInput(chatId, messageText);
            }

        }
        return message;
    }

    private SendMessage handleRegistrationInput(Long chatId, String messageText) {
        String currentStep = registeringUsers.get(chatId);
        switch (currentStep) {
            case "waitingForName":
                userData.put(chatId + "_name", messageText);
                registeringUsers.put(chatId, "waitingForEmail");
                return createResponseMessage(chatId, "Nhập email của bạn: ");

            case "waitingForEmail":
                if (!isValidEmail(messageText)) {
                    return createResponseMessage(chatId, "Email không hợp lệ. Vui lòng nhập lại email của bạn: ");
                }
                userData.put(chatId + "_email", messageText);
                registeringUsers.put(chatId, "waitingForPassword");
                return createResponseMessage(chatId, "Nhập password của bạn: ");

            case "waitingForPassword":
                userData.put(chatId + "_password", messageText);
                registeringUsers.put(chatId, "waitingForPhoneNumber");
                return createResponseMessage(chatId, "Nhập số điện thoại của bạn: ");

            case "waitingForPhoneNumber":
                userData.put(chatId + "_phoneNumber", messageText);
                saveUserData(chatId);
                return createResponseMessage(chatId, "Cảm ơn bạn đã đăng ký!!!");

            default:
                return createResponseMessage(chatId, "Có lỗi xảy ra, vui lòng thử lại!!");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
    private SendMessage createResponseMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId.toString(), text);
        return message;
    }

    private void saveUserData(Long chatId) {
        UserModel user = UserModel.builder()
                .name(userData.get(chatId + "_name"))
                .email(userData.get(chatId + "_email"))
                .password(userData.get(chatId + "_password"))
                .phoneNumber(userData.get(chatId + "_phoneNumber"))
                .role("USER")
                .chatId(chatId)
                .registeredAt(LocalDateTime.now())
                .build();
        try {
            userRepository.save(user);
            System.out.println("Save successfully");
        } catch (Exception e) {
            System.out.println("Error while saving user: " + e.getMessage());
            e.printStackTrace();
        }
        // Xóa dữ liệu đã lưu trữ sau khi lưu vào DB
        userData.remove(chatId + "_name");
        userData.remove(chatId + "_email");
        userData.remove(chatId + "_password");
        userData.remove(chatId + "_phoneNumber");
        registeringUsers.remove(chatId);
    }
}
