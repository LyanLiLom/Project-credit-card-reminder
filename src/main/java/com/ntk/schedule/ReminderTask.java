package com.ntk.schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import com.ntk.model.TransactionModel;
import com.ntk.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReminderTask {

    private final TelegramClient telegramClient;

    private final TransactionRepository TransactionRepository;

    /**
     * Run job every day at 7 AM
     */
    @Scheduled(cron = "0 7 1 * * *", zone = "Asia/Bangkok")
    public void executeTaskEveryDayAt7AM() {
        LocalDateTime cutOffDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);

        Optional<List<TransactionModel>> optionalList2 = TransactionRepository.findByCutOffDate(cutOffDate);

        if(optionalList2.isPresent()) {
            List<TransactionModel> list = optionalList2.get();
            for(TransactionModel trxn : list) {
                sendMessage(trxn);
            }
            System.out.println("Existsed data? : " + list.size() + " - " + cutOffDate.toString());
        }
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    public void sendMessage(TransactionModel transaction) {
        SendMessage message = null;
        String userName = transaction.getUserName();
        long chatId = transaction.getChatId();
        String messageText = "Ngay den han thanh toan cua the tin dung " + transaction.getCreditCardNumber() + " la ngay " + transaction.getCutOffDate() ;

        log.info("===================================================================");
        log.info("Send to username: {}, with chatId: {}", userName, String.valueOf(chatId));
        log.info("Message: {}", messageText);

        // Set variables

        message = SendMessage // Create a message object
            .builder()
            .chatId(chatId)
            .text(messageText)
            .build();

        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            log.error("Reminder send message error - with chatId {} \\n {}",chatId, e);
        }
    }


}
