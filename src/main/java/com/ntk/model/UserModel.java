package com.ntk.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Data
@Document("User")
public class UserModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2323L;

	@Id
	private String id;
	
	@NotNull
	private String name;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String password;
	
	@NotNull
	private String phoneNumber;

	@NotNull
	private String role;

	@NotNull
	private LocalDateTime registeredAt;

	@NotNull
	private Long chatId;


	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + "]";
	}
}
