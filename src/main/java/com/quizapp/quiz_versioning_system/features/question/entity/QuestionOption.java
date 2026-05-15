package com.quizapp.quiz_versioning_system.features.question.entity;

import com.quizapp.quiz_versioning_system.common.entity.BaseEntity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question_options")
public class QuestionOption extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "question_version_id",
            nullable = false)
    private QuestionVersion questionVersion;

    @Column(nullable = false)
    private String optionText;

    @Column(nullable = false)
    private Integer displayOrder;
}