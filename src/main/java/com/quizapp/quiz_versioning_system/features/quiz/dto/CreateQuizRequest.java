package com.quizapp.quiz_versioning_system.features.quiz.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizRequest {

    @NotBlank(message = "Quiz title is required")
    private String title;

    @Valid
    @NotEmpty(message = "Quiz must contain questions")
    private List<QuizQuestionRequest> questions;
}