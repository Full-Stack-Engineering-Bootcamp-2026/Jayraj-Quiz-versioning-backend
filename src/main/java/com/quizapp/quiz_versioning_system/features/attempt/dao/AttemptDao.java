package com.quizapp.quiz_versioning_system.features.attempt.dao;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptAnswer;
import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptSession;
import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;
import com.quizapp.quiz_versioning_system.features.quiz.entity.QuizQuestion;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

public interface AttemptDao {

    User getUserByUuid(UUID userUuid);

    Quiz getQuizByUuid(UUID quizUuid);

    QuizQuestion getQuizQuestionByUuid(
            UUID quizQuestionUuid);

    AttemptSession saveAttemptSession(
            AttemptSession attemptSession);

    List<AttemptAnswer> saveAttemptAnswers(
            List<AttemptAnswer> answers);

    List<AttemptSession> getAttemptsByUser(
            User user);

    AttemptSession getAttemptByUuid(
            UUID attemptUuid);

    List<AttemptAnswer> getAnswersByAttempt(
            AttemptSession attemptSession);
}