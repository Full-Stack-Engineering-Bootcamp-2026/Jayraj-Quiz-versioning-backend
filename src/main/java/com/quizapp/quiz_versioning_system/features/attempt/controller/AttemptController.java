package com.quizapp.quiz_versioning_system.features.attempt.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.quizapp.quiz_versioning_system.common.response.ApiResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.AttemptDetailsResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.AttemptSummaryResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.SubmitAttemptRequest;
import com.quizapp.quiz_versioning_system.features.attempt.service.AttemptService;
import com.quizapp.quiz_versioning_system.security.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attempts")
@RequiredArgsConstructor
public class AttemptController {

    private final AttemptService attemptService;

    private final SecurityUtil securityUtil;

    @PreAuthorize("hasAuthority('QUIZ_ATTEMPT')")
    @PostMapping
    public ResponseEntity<ApiResponse<Object>>
    submitAttempt(
            @Valid @RequestBody
            SubmitAttemptRequest request) {

        UUID userUuid =
                securityUtil.getCurrentUserUuid();

        attemptService.submitAttempt(
                request,
                userUuid);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message(
                                "Attempt submitted successfully")
                        .build());
    }

    @PreAuthorize("hasAuthority('ATTEMPT_VIEW_OWN')")
    @GetMapping
    public ResponseEntity<
            ApiResponse<List<AttemptSummaryResponse>>>
    getMyAttempts() {

        UUID userUuid =
                securityUtil.getCurrentUserUuid();

        List<AttemptSummaryResponse> response =
                attemptService.getMyAttempts(
                        userUuid);

        return ResponseEntity.ok(
                ApiResponse
                        .<List<AttemptSummaryResponse>>
                        builder()
                        .success(true)
                        .message(
                                "Attempts fetched successfully")
                        .data(response)
                        .build());
    }

    @PreAuthorize("hasAuthority('ATTEMPT_VIEW_OWN')")
    @GetMapping("/{attemptUuid}")
    public ResponseEntity<
            ApiResponse<AttemptDetailsResponse>>
    getAttemptByUuid(
            @PathVariable UUID attemptUuid) {

        UUID userUuid =
                securityUtil.getCurrentUserUuid();

        AttemptDetailsResponse response =
                attemptService.getAttemptByUuid(
                        attemptUuid,
                        userUuid);

        return ResponseEntity.ok(
                ApiResponse
                        .<AttemptDetailsResponse>
                        builder()
                        .success(true)
                        .message(
                                "Attempt fetched successfully")
                        .data(response)
                        .build());
    }
}