package com.ntk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Document("CreditCardType")
@Data
public class CreditCardTypeModel {
    @Id
    private String id;
    
    @NotBlank
    private String name;

    private String bankId;

    private Integer interstFreePeriod;

}
