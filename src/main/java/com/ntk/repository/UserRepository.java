package com.ntk.repository;

import java.util.Optional;

import com.ntk.model.TransactionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ntk.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, Long> {
	
	Optional<UserModel> findById(@Param("id")Long id);
		
	void deleteById(@Param("id")Long id);
	
	Optional<UserModel> findByEmail(@Param("email")String email);
}
