package com.ntk.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntk.dto.user.UserRequest;
import com.ntk.model.UserModel;
import com.ntk.repository.UserRepository;
import com.ntk.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserModel insertUser(UserRequest request) {
		log.info("Saving user {} ", request.toString());
		UserModel model = null;
		try {
			model = userRepository.save(mappingUser(request));
		} catch (Exception e) {
			log.error("Insert user error: {} ", e);
			return null;
		}
		return model;
	}

	@Override
	public UserModel findById(Long id) {
		log.info("Finding user id {} ", id);
		return findUserById(id);
	}

	@Override
	public UserModel updateUser(UserRequest request) {
		log.info("Finding user id {} ", request.getId().toString());
		UserModel model = findUserById(request.getId());
		if(model != null) {
			//Mapping user
			model = mappingUser(request);
			log.info("Saving user {} ", model);
			userRepository.save(model);
			return model;
		}
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		log.info("Finding user id {} ", id.toString());
		UserModel model = findUserById(id);
		if(model != null) {
			log.info("Deleting user {} ", model);
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private UserModel findUserById(Long id) {
		Optional<UserModel> optionalModel = userRepository.findById(id);
		if(optionalModel.isPresent()) {
			log.info("Found user id {} ", id.toString());
			return optionalModel.get();
		}
		return null;
	}

	private UserModel mappingUser(UserRequest request) {
		UserModel model = UserModel.builder()
									.email(request.getEmail())
									.name(request.getName())
									.password("password")
									.role(request.getRole())
									.build();
		log.info("Result mapping user {}", model.toString());
		return model;

	}

	@Override
	public UserModel findByEmail(String email) {
		Optional<UserModel> optionalModel = userRepository.findByEmail(email);
		if(optionalModel.isPresent()) {
			log.info("Found user with email {} ", optionalModel.get());
			return optionalModel.get();
		}
		return null;
	}
}
