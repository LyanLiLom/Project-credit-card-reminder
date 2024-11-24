package com.ntk.dto.user;

import com.ntk.dto.AbstractResponse;

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
public class UserResponse {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String role;
}
