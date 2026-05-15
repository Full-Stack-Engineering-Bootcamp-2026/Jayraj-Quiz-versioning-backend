package com.quizapp.quiz_versioning_system.features.quiz.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizQuestionRequest {

    @NotNull(message = "Question UUID is required")
    private UUID questionUuid;

    private Integer displayOrder;
}