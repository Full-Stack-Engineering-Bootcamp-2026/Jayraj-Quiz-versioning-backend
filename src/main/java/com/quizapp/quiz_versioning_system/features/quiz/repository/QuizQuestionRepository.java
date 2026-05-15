package com.quizapp.quiz_versioning_system.features.quiz.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;
import com.quizapp.quiz_versioning_system.features.quiz.entity.QuizQuestion;

public interface QuizQuestionRepository
                extends JpaRepository<QuizQuestion, Long> {

        List<QuizQuestion> findByQuizOrderByDisplayOrder(
                        Quiz quiz);

        Optional<QuizQuestion> findByUuid(UUID uuid);
}