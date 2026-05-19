package com.quizapp.quiz_versioning_system.features.quiz.dto;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.features.question.dto.QuestionOptionResponse;
import com.quizapp.quiz_versioning_system.features.question.enums.QuestionType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuizQuestionResponse {

    private UUID quizQuestionUuid;

    private UUID questionUuid;

    private UUID questionVersionUuid;

    private Integer versionNumber;

    private String questionText;

    private QuestionType questionType;

    private List<QuestionOptionResponse> options;

    private Integer displayOrder;
}