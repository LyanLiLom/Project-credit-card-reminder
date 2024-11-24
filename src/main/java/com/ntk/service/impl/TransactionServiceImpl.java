package com.ntk.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ntk.constant.StatusCode;
import com.ntk.dto.transaction.InsertTransactionRequest;
import com.ntk.dto.user.UserRequest;
import com.ntk.model.TransactionModel;
import com.ntk.model.UserModel;
import com.ntk.repository.TransactionRepository;
import com.ntk.repository.UserRepository;
import com.ntk.service.TransactionService;
import com.ntk.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;

	@Override
	public TransactionModel insertTransaction(InsertTransactionRequest request) {
		// TransactionModel model = null;
		// try {
		// 	model = transactionRepository.save()
		// }
		// return null;
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransactionModel findByCreditCardNumber(String creditCardNumber) {
		Optional<TransactionModel> optionalModel = transactionRepository.findByCreditCardNumber(creditCardNumber);
		
		if(optionalModel.isPresent()) {
			return optionalModel.get();
		}
		return null;
	}

	@Override
	public TransactionModel insertTransactionTelegram(Update update) {
		TransactionModel model = null;
		try {
			model = mappingTransactionTelegram(update);
			transactionRepository.save(model);
		} catch (Exception e) {
			log.error("Error insert transaction via telegram : {}", e);
		}
		return model;
	}

	private TransactionModel mappingTransactionTelegram(Update update) {
		String userName = update.getMessage().getChat().getUserName();
		Long chatId = update.getMessage().getChatId();
		String creditCardNumber = update.getMessage().getText();

		TransactionModel model =  TransactionModel
									.builder()
									.chatId(chatId)
									.userName(userName)
									.creditCardNumber(creditCardNumber)
									.creditCardType(null)
									.cutOffDate(null)
									.build();

		return model;
	}

}
