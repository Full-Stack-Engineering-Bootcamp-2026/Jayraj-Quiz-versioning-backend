package com.quizapp.quiz_versioning_system.features.question.service;

import java.util.UUID;

import com.quizapp.quiz_versioning_system.features.question.dto.CreateQuestionRequest;
import com.quizapp.quiz_versioning_system.features.question.dto.QuestionResponse;

public interface QuestionService {

    QuestionResponse createQuestion(
            CreateQuestionRequest request,
            UUID userUuid);

    QuestionResponse editQuestion(
            UUID questionUuid,
            CreateQuestionRequest request,
            UUID userUuid);
}