package com.quizapp.quiz_versioning_system.features.attempt.entity;

import java.time.LocalDateTime;

import com.quizapp.quiz_versioning_system.common.entity.BaseEntity;
import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attempt_sessions")
public class AttemptSession extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Boolean isSubmitted = false;

    private LocalDateTime submittedAt;
}