package com.quizapp.quiz_versioning_system.features.attempt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptAnswer;
import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptSession;

public interface AttemptAnswerRepository
        extends JpaRepository<AttemptAnswer, Long> {

    List<AttemptAnswer>
    findByAttemptSession(
            AttemptSession attemptSession);
}