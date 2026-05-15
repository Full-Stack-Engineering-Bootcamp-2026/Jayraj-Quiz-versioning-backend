package com.quizapp.quiz_versioning_system.features.question.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.question.entity.Question;

public interface QuestionRepository
        extends JpaRepository<Question, Long> {

    Optional<Question> findByUuid(UUID uuid);
}