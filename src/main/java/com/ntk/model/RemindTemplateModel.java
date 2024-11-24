package com.ntk.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("question_history")
public class RemindTemplateModel {
	@Id
	private Long id;

	@NotBlank
	private String content;

}
