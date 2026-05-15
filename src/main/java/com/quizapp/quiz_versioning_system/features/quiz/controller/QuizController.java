package com.quizapp.quiz_versioning_system.features.quiz.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.quizapp.quiz_versioning_system.common.response.ApiResponse;
import com.quizapp.quiz_versioning_system.features.quiz.dto.CreateQuizRequest;
import com.quizapp.quiz_versioning_system.features.quiz.dto.QuizResponse;
import com.quizapp.quiz_versioning_system.features.quiz.service.QuizService;
import com.quizapp.quiz_versioning_system.security.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor
public class QuizController {

        private final QuizService quizService;

        private final SecurityUtil securityUtil;

        @PreAuthorize("hasAuthority('QUIZ_CREATE')")
        @PostMapping
        public ResponseEntity<ApiResponse<QuizResponse>> createQuiz(
                        @Valid @RequestBody CreateQuizRequest request) {

                UUID userUuid = securityUtil.getCurrentUserUuid();

                QuizResponse response = quizService.createQuiz(
                                request,
                                userUuid);

                return ResponseEntity.ok(
                                ApiResponse.<QuizResponse>builder()
                                                .success(true)
                                                .message(
                                                                "Quiz created successfully")
                                                .data(response)
                                                .build());
        }

        @PreAuthorize("hasAuthority('QUIZ_ATTEMPT')")
        @GetMapping
        public ResponseEntity<ApiResponse<List<QuizResponse>>> getAllQuizzes() {

                List<QuizResponse> response = quizService.getAllQuizzes();

                return ResponseEntity.ok(
                                ApiResponse.<List<QuizResponse>>builder()
                                                .success(true)
                                                .message("Quizzes fetched successfully")
                                                .data(response)
                                                .build());
        }

        @PreAuthorize("hasAuthority('QUIZ_ATTEMPT')")
        @GetMapping("/{quizUuid}")
        public ResponseEntity<ApiResponse<QuizResponse>> getQuizByUuid(
                        @PathVariable UUID quizUuid) {

                QuizResponse response = quizService.getQuizByUuid(
                                quizUuid);

                return ResponseEntity.ok(
                                ApiResponse.<QuizResponse>builder()
                                                .success(true)
                                                .message("Quiz fetched successfully")
                                                .data(response)
                                                .build());
        }
}