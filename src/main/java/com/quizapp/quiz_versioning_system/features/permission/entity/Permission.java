package com.quizapp.quiz_versioning_system.features.permission.entity;

import com.quizapp.quiz_versioning_system.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;
}