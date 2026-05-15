package com.quizapp.quiz_versioning_system.features.attempt.service;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.features.attempt.dto.AttemptDetailsResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.AttemptSummaryResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.SubmitAttemptRequest;

public interface AttemptService {

    void submitAttempt(
            SubmitAttemptRequest request,
            UUID userUuid);

    List<AttemptSummaryResponse> getMyAttempts(
            UUID userUuid);

    AttemptDetailsResponse getAttemptByUuid(
            UUID attemptUuid,
            UUID userUuid);
}