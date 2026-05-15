package com.quizapp.quiz_versioning_system.features.quiz.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizResponse {

    private UUID quizUuid;

    private String title;

    private List<QuizQuestionResponse> questions;
}