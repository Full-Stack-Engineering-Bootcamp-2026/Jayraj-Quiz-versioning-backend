package com.quizapp.quiz_versioning_system.features.question.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.quizapp.quiz_versioning_system.common.response.ApiResponse;
import com.quizapp.quiz_versioning_system.features.question.dto.CreateQuestionRequest;
import com.quizapp.quiz_versioning_system.features.question.dto.QuestionResponse;
import com.quizapp.quiz_versioning_system.features.question.service.QuestionService;
import com.quizapp.quiz_versioning_system.security.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

        private final QuestionService questionService;

        private final SecurityUtil securityUtil;

        @PreAuthorize("hasAuthority('QUESTION_CREATE')")
        @PostMapping
        public ResponseEntity<ApiResponse<QuestionResponse>> createQuestion(
                        @Valid @RequestBody CreateQuestionRequest request) {

                UUID userUuid = securityUtil.getCurrentUserUuid();

                QuestionResponse response = questionService.createQuestion(
                                request,
                                userUuid);

                return ResponseEntity.ok(
                                ApiResponse.<QuestionResponse>builder()
                                                .success(true)
                                                .message(
                                                                "Question created successfully")
                                                .data(response)
                                                .build());
        }

        @PreAuthorize("hasAuthority('QUESTION_EDIT')")
        @PutMapping("/{questionUuid}")
        public ResponseEntity<ApiResponse<QuestionResponse>> editQuestion(
                        @PathVariable UUID questionUuid,
                        @Valid @RequestBody CreateQuestionRequest request) {

                UUID userUuid = securityUtil.getCurrentUserUuid();

                QuestionResponse response = questionService.editQuestion(
                                questionUuid,
                                request,
                                userUuid);

                return ResponseEntity.ok(
                                ApiResponse.<QuestionResponse>builder()
                                                .success(true)
                                                .message(
                                                                "Question version created successfully")
                                                .data(response)
                                                .build());
        }

        @PreAuthorize("hasAuthority('QUIZ_CREATE')")
        @GetMapping
        public ResponseEntity<ApiResponse<List<QuestionResponse>>> getAllQuestions() {

                List<QuestionResponse> response = questionService.getAllQuestions();

                return ResponseEntity.ok(
                                ApiResponse.<List<QuestionResponse>>builder()
                                                .success(true)
                                                .message("Questions fetched successfully")
                                                .data(response)
                                                .build());
        }

        @PreAuthorize("hasAuthority('QUIZ_CREATE')")
        @GetMapping("/{questionUuid}")
        public ResponseEntity<ApiResponse<QuestionResponse>> getQuestionByUuid(
                        @PathVariable UUID questionUuid) {

                QuestionResponse response = questionService.getQuestionByUuid(
                                questionUuid);

                return ResponseEntity.ok(
                                ApiResponse.<QuestionResponse>builder()
                                                .success(true)
                                                .message("Question fetched successfully")
                                                .data(response)
                                                .build());
        }
}