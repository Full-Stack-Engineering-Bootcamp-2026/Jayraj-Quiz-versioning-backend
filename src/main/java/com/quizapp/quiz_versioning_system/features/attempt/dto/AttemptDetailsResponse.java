package com.quizapp.quiz_versioning_system.features.attempt.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AttemptDetailsResponse {

    private UUID attemptUuid;

    private UUID quizUuid;

    private String quizTitle;

    private LocalDateTime submittedAt;

    private List<AttemptAnswerResponse> answers;
}