package com.ntk.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ntk.dto.transaction.InsertTransactionRequest;
import com.ntk.model.TransactionModel;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper( TransactionMapper.class );
 
    @Mapping(source = "creditCardNumber", target = "creditCardNumber")
    InsertTransactionRequest insertRequestToModel(TransactionModel model);
}
