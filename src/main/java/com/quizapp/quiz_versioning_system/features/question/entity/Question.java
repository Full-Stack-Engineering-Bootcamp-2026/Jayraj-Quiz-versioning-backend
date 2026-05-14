package com.quizapp.quiz_versioning_system.features.question.entity;

import com.quizapp.quiz_versioning_system.common.entity.BaseEntity;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    private Boolean isDeleted = false;
}