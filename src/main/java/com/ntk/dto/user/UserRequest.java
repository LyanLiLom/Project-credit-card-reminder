package com.ntk.dto.user;

import com.ntk.dto.AbstractRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest extends AbstractRequest{
	
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6, max = 15)
	private String password;
	
	@NotBlank
	private String role;
}
