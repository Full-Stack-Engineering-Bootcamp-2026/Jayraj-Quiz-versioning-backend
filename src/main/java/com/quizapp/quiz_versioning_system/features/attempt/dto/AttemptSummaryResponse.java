package com.quizapp.quiz_versioning_system.features.attempt.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AttemptSummaryResponse {

    private UUID attemptUuid;

    private UUID quizUuid;

    private String quizTitle;

    private Boolean isSubmitted;

    private LocalDateTime submittedAt;
}