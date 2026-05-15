package com.quizapp.quiz_versioning_system.features.quiz.entity;

import com.quizapp.quiz_versioning_system.common.entity.BaseEntity;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quizzes")
public class Quiz extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(nullable = false)
    private Boolean isActive = true;
}