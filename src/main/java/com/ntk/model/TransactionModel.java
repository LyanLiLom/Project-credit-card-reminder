package com.ntk.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("Transaction")
public class TransactionModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 123L;

	@Id
	private String id;
	
	@NotNull
	private String creditCardNumber;

	private String owerName;
	
	private String userName;

	@NotNull
	private Long chatId;

	
	private Long amount;

	
	private LocalDateTime cutOffDate;
	
	@NotNull
	private LocalDateTime transactionDate;

	private LocalDateTime createdAt = LocalDateTime.now();
	
	@NotNull
	private String creditCardType;
	

	private String status = "1";

}
