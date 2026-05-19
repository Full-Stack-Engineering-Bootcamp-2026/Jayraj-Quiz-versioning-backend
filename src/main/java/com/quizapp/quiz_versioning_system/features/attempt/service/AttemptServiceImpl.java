package com.quizapp.quiz_versioning_system.features.attempt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.exception.BadRequestException;
import com.quizapp.quiz_versioning_system.features.attempt.dao.AttemptDao;
import com.quizapp.quiz_versioning_system.features.attempt.dto.AttemptAnswerRequest;
import com.quizapp.quiz_versioning_system.features.attempt.dto.AttemptAnswerResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.AttemptDetailsResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.AttemptSummaryResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.QuestionOptionResponse;
import com.quizapp.quiz_versioning_system.features.attempt.dto.SubmitAttemptRequest;
import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptAnswer;
import com.quizapp.quiz_versioning_system.features.attempt.entity.AttemptSession;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionOption;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.features.question.repository.QuestionOptionRepository;
import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;
import com.quizapp.quiz_versioning_system.features.quiz.entity.QuizQuestion;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttemptServiceImpl implements AttemptService {

        private final AttemptDao attemptDao;

        private final QuestionOptionRepository questionOptionRepository;

        @Override
        public void submitAttempt(
                        SubmitAttemptRequest request,
                        UUID userUuid) {

                User user = attemptDao.getUserByUuid(userUuid);

                Quiz quiz = attemptDao.getQuizByUuid(
                                request.getQuizUuid());

                AttemptSession attemptSession = new AttemptSession();

                attemptSession.setQuiz(quiz);

                attemptSession.setUser(user);

                attemptSession.setIsSubmitted(true);

                attemptSession.setSubmittedAt(
                                LocalDateTime.now());

                AttemptSession savedAttempt = attemptDao.saveAttemptSession(
                                attemptSession);

                List<AttemptAnswer> answers = request.getAnswers()
                                .stream()
                                .map(answerRequest -> buildAttemptAnswer(
                                                answerRequest,
                                                savedAttempt))
                                .toList();

                attemptDao.saveAttemptAnswers(
                                answers);
        }

        @Override
        public List<AttemptSummaryResponse> getMyAttempts(UUID userUuid) {

                User user = attemptDao.getUserByUuid(userUuid);

                List<AttemptSession> attempts = attemptDao.getAttemptsByUser(
                                user);

                return attempts.stream()
                                .map(this::buildSummaryResponse)
                                .toList();
        }

        @Override
        public AttemptDetailsResponse getAttemptByUuid(
                        UUID attemptUuid,
                        UUID userUuid) {

                AttemptSession attempt = attemptDao.getAttemptByUuid(
                                attemptUuid);

                validateAttemptOwnership(
                                attempt,
                                userUuid);

                List<AttemptAnswer> answers = attemptDao.getAnswersByAttempt(
                                attempt);

                return buildAttemptDetailsResponse(
                                attempt,
                                answers);
        }

        private AttemptAnswer buildAttemptAnswer(
                        AttemptAnswerRequest request,
                        AttemptSession attemptSession) {

                QuizQuestion quizQuestion = attemptDao.getQuizQuestionByUuid(
                                request.getQuizQuestionUuid());

                validateQuizQuestionBelongsToQuiz(
                                quizQuestion,
                                attemptSession.getQuiz());

                AttemptAnswer answer = new AttemptAnswer();

                answer.setAttemptSession(
                                attemptSession);

                answer.setQuizQuestion(
                                quizQuestion);

                answer.setAnswerText(
                                request.getAnswerText());

                return answer;
        }

        private void validateQuizQuestionBelongsToQuiz(
                        QuizQuestion quizQuestion,
                        Quiz quiz) {

                if (!quizQuestion.getQuiz()
                                .getId()
                                .equals(quiz.getId())) {

                        throw new BadRequestException(
                                        "Quiz question does not belong to quiz");
                }
        }

        private void validateAttemptOwnership(
                        AttemptSession attempt,
                        UUID userUuid) {

                if (!attempt.getUser()
                                .getUuid()
                                .equals(userUuid)) {

                        throw new BadRequestException(
                                        "You cannot access this attempt");
                }
        }

        private AttemptSummaryResponse buildSummaryResponse(
                        AttemptSession attempt) {

                return AttemptSummaryResponse.builder()

                                .attemptUuid(
                                                attempt.getUuid())

                                .quizUuid(
                                                attempt.getQuiz().getUuid())

                                .quizTitle(
                                                attempt.getQuiz().getTitle())

                                .isSubmitted(
                                                attempt.getIsSubmitted())

                                .submittedAt(
                                                attempt.getSubmittedAt())

                                .build();
        }

        private AttemptDetailsResponse buildAttemptDetailsResponse(
                        AttemptSession attempt,
                        List<AttemptAnswer> answers) {

                return AttemptDetailsResponse.builder()

                                .attemptUuid(
                                                attempt.getUuid())

                                .quizUuid(
                                                attempt.getQuiz().getUuid())

                                .quizTitle(
                                                attempt.getQuiz().getTitle())

                                .submittedAt(
                                                attempt.getSubmittedAt())

                                .answers(
                                                answers.stream()
                                                                .map(this::buildAnswerResponse)
                                                                .toList())

                                .build();
        }

        private AttemptAnswerResponse buildAnswerResponse(
                        AttemptAnswer answer) {

                QuizQuestion quizQuestion = answer.getQuizQuestion();

                QuestionVersion version = quizQuestion.getQuestionVersion();

                List<QuestionOptionResponse> options = questionOptionRepository
                                .findByQuestionVersionOrderByDisplayOrder(
                                                version)
                                .stream()
                                .map(option -> QuestionOptionResponse
                                                .builder()
                                                .optionText(
                                                                option.getOptionText())
                                                .displayOrder(
                                                                option.getDisplayOrder())
                                                .build())
                                .toList();

                return AttemptAnswerResponse.builder()

                                .quizQuestionUuid(
                                                quizQuestion.getUuid())

                                .questionUuid(
                                                version.getQuestion()
                                                                .getUuid())

                                .questionVersionUuid(
                                                version.getUuid())

                                .versionNumber(
                                                version.getVersionNumber())

                                .questionText(
                                                version.getQuestionText())

                                .questionType(
                                                version.getQuestionType())

                                .options(options)

                                .submittedAnswer(
                                                answer.getAnswerText())

                                .build();
        }
}