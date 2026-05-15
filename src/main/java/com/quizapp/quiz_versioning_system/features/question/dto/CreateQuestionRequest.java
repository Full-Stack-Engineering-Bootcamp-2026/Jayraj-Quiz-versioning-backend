package com.quizapp.quiz_versioning_system.features.question.dto;

import java.util.List;

import com.quizapp.quiz_versioning_system.features.question.enums.QuestionType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionRequest {

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotNull(message = "Question type is required")
    private QuestionType questionType;

    @Valid
    private List<QuestionOptionRequest> options;
}