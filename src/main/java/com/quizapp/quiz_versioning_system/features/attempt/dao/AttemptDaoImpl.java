package com.quizapp.quiz_versioning_system.features.attempt.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.quiz_versioning_system.common.exception.ResourceNotFoundException;
import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptAnswer;
import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptSession;
import com.quizapp.quiz_versioning_system.features.attempt.repository.AttemptAnswerRepository;
import com.quizapp.quiz_versioning_system.features.attempt.repository.AttemptSessionRepository;
import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;
import com.quizapp.quiz_versioning_system.features.quiz.entity.QuizQuestion;
import com.quizapp.quiz_versioning_system.features.quiz.repository.QuizQuestionRepository;
import com.quizapp.quiz_versioning_system.features.quiz.repository.QuizRepository;
import com.quizapp.quiz_versioning_system.features.user.entity.User;
import com.quizapp.quiz_versioning_system.features.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AttemptDaoImpl implements AttemptDao {

    private final UserRepository userRepository;

    private final QuizRepository quizRepository;

    private final QuizQuestionRepository
            quizQuestionRepository;

    private final AttemptSessionRepository
            attemptSessionRepository;

    private final AttemptAnswerRepository
            attemptAnswerRepository;

    @Override
    public User getUserByUuid(UUID userUuid) {

        return userRepository.findByUuid(userUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));
    }

    @Override
    public Quiz getQuizByUuid(UUID quizUuid) {

        return quizRepository.findByUuid(quizUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz not found"));
    }

    @Override
    public QuizQuestion getQuizQuestionByUuid(
            UUID quizQuestionUuid) {

        return quizQuestionRepository
                .findByUuid(quizQuestionUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quiz question not found"));
    }

    @Override
    @Transactional
    public AttemptSession saveAttemptSession(
            AttemptSession attemptSession) {

        return attemptSessionRepository
                .save(attemptSession);
    }

    @Override
    @Transactional
    public List<AttemptAnswer> saveAttemptAnswers(
            List<AttemptAnswer> answers) {

        return attemptAnswerRepository
                .saveAll(answers);
    }

    @Override
    public List<AttemptSession> getAttemptsByUser(
            User user) {

        return attemptSessionRepository
                .findByUserOrderByCreatedAtDesc(
                        user);
    }

    @Override
    public AttemptSession getAttemptByUuid(
            UUID attemptUuid) {

        return attemptSessionRepository
                .findByUuid(attemptUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attempt not found"));
    }

    @Override
    public List<AttemptAnswer> getAnswersByAttempt(
            AttemptSession attemptSession) {

        return attemptAnswerRepository
                .findByAttemptSession(
                        attemptSession);
    }
}