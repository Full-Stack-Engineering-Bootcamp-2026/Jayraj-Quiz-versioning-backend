package com.quizapp.quiz_versioning_system.features.quiz.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizQuestionResponse {

    private UUID questionUuid;

    private UUID questionVersionUuid;

    private Integer versionNumber;

    private String questionText;

    private Integer displayOrder;
}