package com.ntk.service;

import org.springframework.stereotype.Service;

import com.ntk.dto.user.UserRequest;
import com.ntk.model.UserModel;

@Service
public interface UserService {
	public UserModel findById(Long id);

	public UserModel insertUser(UserRequest request);
		
	public UserModel updateUser(UserRequest id);
	
	public boolean deleteById(Long id);
	
	public UserModel findByEmail(String email);

}
