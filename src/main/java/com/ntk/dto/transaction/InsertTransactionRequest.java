package com.ntk.dto.transaction;

import com.ntk.dto.AbstractRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
public class InsertTransactionRequest extends AbstractRequest{
	String creditCardNumber;
	Long cardTypeId;
	String userName;
	String ownerName;
	Long amount;
	String transactionDate;
}
	