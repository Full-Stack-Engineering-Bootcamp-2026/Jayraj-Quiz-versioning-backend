package com.quizapp.quiz_versioning_system.features.quiz.entity;

import com.quizapp.quiz_versioning_system.common.entity.BaseEntity;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quiz_questions")
public class QuizQuestion extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "question_version_id",
            nullable = false)
    private QuestionVersion questionVersion;

    @Column(nullable = false)
    private Integer displayOrder;
}