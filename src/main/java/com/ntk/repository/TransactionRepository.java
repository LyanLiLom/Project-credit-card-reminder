package com.ntk.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ntk.model.TransactionModel;
import java.util.List;
import java.time.LocalDateTime;


@Repository
public interface TransactionRepository extends MongoRepository<TransactionModel, String>{
    
	Optional<TransactionModel> findByCreditCardNumber(@Param("creditCardNumber")String creditCardNumber);
	
	Optional<List<TransactionModel>> findByCutOffDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

	Optional<List<TransactionModel>> findByCutOffDate(@Param("cutOffDate")LocalDateTime cutOffDate);
}
