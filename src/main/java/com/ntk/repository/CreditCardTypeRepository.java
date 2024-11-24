package com.ntk.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ntk.model.CreditCardTypeModel;

@Repository
public interface CreditCardTypeRepository extends MongoRepository<CreditCardTypeModel, String>{
    
	Optional<CreditCardTypeModel> findByBankId(@Param("bankId")String bankId);
}
