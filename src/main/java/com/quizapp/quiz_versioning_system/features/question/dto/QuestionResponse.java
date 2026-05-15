package com.quizapp.quiz_versioning_system.features.question.dto;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.features.question.enums.QuestionType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionResponse {

    private UUID questionUuid;

    private UUID questionVersionUuid;

    private Integer versionNumber;

    private String questionText;

    private QuestionType questionType;

    private List<QuestionOptionResponse> options;
}