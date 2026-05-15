package com.quizapp.quiz_versioning_system.features.attempt.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitAttemptRequest {

    @NotNull(message = "Quiz UUID is required")
    private UUID quizUuid;

    @Valid
    @NotEmpty(message = "Answers are required")
    private List<AttemptAnswerRequest> answers;
}