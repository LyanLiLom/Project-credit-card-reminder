package com.ntk.model;

import java.io.Serializable;
import java.time.LocalDateTime;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("CreditCardOwner")
public class CreditCardOwnerModel implements Serializable{
	
	private static final long serialVersionUID = 123L;

	
	@Id
	private Long id;
	
	@NotNull
	private String cardNumber;
	
	@NotNull
	private String ownerName;
	
	@NotNull
	private LocalDateTime expiredDate;
	
	@NotNull
	private String phoneNumber;
	
	@NotNull
	private Long creditCardTypeId;
}
