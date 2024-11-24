package com.ntk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DataResponse extends AbstractResponse {

	public DataResponse(String status, String message, Object data) {
		super(status, message, data);
	}
}
