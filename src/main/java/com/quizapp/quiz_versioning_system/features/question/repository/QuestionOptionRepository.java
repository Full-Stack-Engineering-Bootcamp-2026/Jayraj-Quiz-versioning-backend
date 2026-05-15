package com.quizapp.quiz_versioning_system.features.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.question.entity.QuestionOption;

public interface QuestionOptionRepository
        extends JpaRepository<QuestionOption, Long> {
}