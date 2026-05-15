package com.quizapp.quiz_versioning_system.features.attempt.entity;

import com.quizapp.quiz_versioning_system.common.entity.BaseEntity;
import com.quizapp.quiz_versioning_system.features.quiz.entity.QuizQuestion;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attempt_answers")
public class AttemptAnswer extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "attempt_session_id",
            nullable = false)
    private AttemptSession attemptSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "quiz_question_id",
            nullable = false)
    private QuizQuestion quizQuestion;

    @Column(columnDefinition = "TEXT")
    private String answerText;
}