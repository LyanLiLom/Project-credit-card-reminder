package com.ntk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder

@Document("user_roles")
public class UserRoleModel {
	
    @Id
    private Long id;
    
    private String name;

}