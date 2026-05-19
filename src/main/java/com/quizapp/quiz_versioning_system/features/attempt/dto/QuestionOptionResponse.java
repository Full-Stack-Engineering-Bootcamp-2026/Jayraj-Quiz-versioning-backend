package com.quizapp.quiz_versioning_system.features.attempt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionOptionResponse {

    private String optionText;

    private Integer displayOrder;
}