package com.quizapp.quiz_versioning_system.features.question.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionOptionResponse {

    private UUID optionUuid;

    private String optionText;

    private Integer displayOrder;
}