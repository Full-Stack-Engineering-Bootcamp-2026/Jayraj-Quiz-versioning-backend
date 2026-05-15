package com.quizapp.quiz_versioning_system.features.attempt.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptSession;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

public interface AttemptSessionRepository
        extends JpaRepository<AttemptSession, Long> {

    Optional<AttemptSession> findByUuid(UUID uuid);

    List<AttemptSession>
    findByUserOrderByCreatedAtDesc(User user);
}