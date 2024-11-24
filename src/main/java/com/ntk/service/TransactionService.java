package com.ntk.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ntk.dto.transaction.InsertTransactionRequest;
import com.ntk.model.TransactionModel;

@Service
public interface TransactionService {
	
	public TransactionModel insertTransaction(InsertTransactionRequest request);
		
	public boolean deleteById(Long id);
	
	public TransactionModel findByCreditCardNumber(String creditCardNumber);

	public TransactionModel insertTransactionTelegram(Update udpate);
}
