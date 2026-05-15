package com.quizapp.quiz_versioning_system.features.attempt.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttemptAnswerRequest {

    @NotNull(message = "Quiz question UUID is required")
    private UUID quizQuestionUuid;

    @NotBlank(message = "Answer is required")
    private String answerText;
}