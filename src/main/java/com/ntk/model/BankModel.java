package com.ntk.model;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("Bank")
public class BankModel {
    @Id
    private Long id;
    
    @NotBlank
    private String name;

    @NotBlank
    private String bankPrefix;
}
