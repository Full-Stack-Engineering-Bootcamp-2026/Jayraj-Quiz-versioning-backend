package com.quizapp.quiz_versioning_system.features.question.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionOptionRequest {

    @NotBlank(message = "Option text is required")
    private String optionText;

    private Integer displayOrder;
}