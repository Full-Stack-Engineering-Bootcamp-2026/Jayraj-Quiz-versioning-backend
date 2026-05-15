package com.quizapp.quiz_versioning_system.features.quiz.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;

public interface QuizRepository
                extends JpaRepository<Quiz, Long> {

        Optional<Quiz> findByUuid(UUID uuid);
}