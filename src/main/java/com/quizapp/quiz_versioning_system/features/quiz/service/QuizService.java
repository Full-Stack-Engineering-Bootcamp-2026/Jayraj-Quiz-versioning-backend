package com.quizapp.quiz_versioning_system.features.quiz.service;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.features.quiz.dto.CreateQuizRequest;
import com.quizapp.quiz_versioning_system.features.quiz.dto.QuizResponse;

public interface QuizService {

    QuizResponse createQuiz(
            CreateQuizRequest request,
            UUID userUuid);

    List<QuizResponse> getAllQuizzes();

    QuizResponse getQuizByUuid(UUID quizUuid);
}