package com.ntk.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class AbstractResponse {

	LocalDateTime timestamp;
	String status;
	String messgage;
	Object data;
	
	public AbstractResponse(String status, String message, Object data) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.messgage = message;
		this.data = data;
	}
}
