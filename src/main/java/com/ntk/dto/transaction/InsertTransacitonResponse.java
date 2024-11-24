package com.ntk.dto.transaction;

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
public class InsertTransacitonResponse{
	String cardNumber;
	String userName;
	String status;
	String transactionDate;
	String cutOffDate;
}
