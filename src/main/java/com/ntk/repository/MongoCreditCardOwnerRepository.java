package com.ntk.repository;

import com.ntk.model.CreditCardOwnerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoCreditCardOwnerRepository extends MongoRepository<CreditCardOwnerModel,Long> {

}
