package com.ntk.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractRequest {
	String requestId;
	LocalDateTime timestamp;
	
	public AbstractRequest(String id) {
		this.timestamp = LocalDateTime.now();
		this.requestId = id;
	}
}
