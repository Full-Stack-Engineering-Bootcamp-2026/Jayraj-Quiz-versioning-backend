package com.quizapp.quiz_versioning_system.features.question.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.question.entity.Question;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;

public interface QuestionVersionRepository
        extends JpaRepository<QuestionVersion, Long> {

    Optional<QuestionVersion>
    findTopByQuestionOrderByVersionNumberDesc(
            Question question);
}