package com.quizapp.quiz_versioning_system.features.quiz.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.features.question.entity.Question;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.features.quiz.dao.QuizDao;
import com.quizapp.quiz_versioning_system.features.quiz.dto.CreateQuizRequest;
import com.quizapp.quiz_versioning_system.features.quiz.dto.QuizQuestionRequest;
import com.quizapp.quiz_versioning_system.features.quiz.dto.QuizQuestionResponse;
import com.quizapp.quiz_versioning_system.features.quiz.dto.QuizResponse;
import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;
import com.quizapp.quiz_versioning_system.features.quiz.entity.QuizQuestion;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

        private final QuizDao quizDao;

        @Override
        public QuizResponse createQuiz(
                        CreateQuizRequest request,
                        UUID userUuid) {

                User user = quizDao.getUserByUuid(userUuid);

                Quiz quiz = new Quiz();

                quiz.setTitle(request.getTitle());

                quiz.setCreatedBy(user);

                Quiz savedQuiz = quizDao.saveQuiz(quiz);

                List<QuizQuestion> quizQuestions = request.getQuestions()
                                .stream()
                                .map(questionRequest -> buildQuizQuestion(
                                                questionRequest,
                                                savedQuiz))
                                .toList();

                List<QuizQuestion> savedQuizQuestions = quizDao.saveQuizQuestions(
                                quizQuestions);

                return buildQuizResponse(
                                savedQuiz,
                                savedQuizQuestions);
        }

        private QuizQuestion buildQuizQuestion(
                        QuizQuestionRequest request,
                        Quiz quiz) {

                Question question = quizDao.getQuestionByUuid(
                                request.getQuestionUuid());

                QuestionVersion latestVersion = quizDao.getLatestQuestionVersion(
                                question);

                QuizQuestion quizQuestion = new QuizQuestion();

                quizQuestion.setQuiz(quiz);

                quizQuestion.setQuestionVersion(
                                latestVersion);

                quizQuestion.setDisplayOrder(
                                request.getDisplayOrder());

                return quizQuestion;
        }

        private QuizResponse buildQuizResponse(
                        Quiz quiz,
                        List<QuizQuestion> quizQuestions) {

                return QuizResponse.builder()

                                .quizUuid(quiz.getUuid())

                                .title(quiz.getTitle())

                                .questions(
                                                quizQuestions.stream()
                                                                .map(this::buildQuestionResponse)
                                                                .toList())

                                .build();
        }

        private QuizQuestionResponse buildQuestionResponse(
                        QuizQuestion quizQuestion) {

                QuestionVersion version = quizQuestion.getQuestionVersion();

                return QuizQuestionResponse.builder()

                                .questionUuid(
                                                version.getQuestion()
                                                                .getUuid())

                                .questionVersionUuid(
                                                version.getUuid())

                                .versionNumber(
                                                version.getVersionNumber())

                                .questionText(
                                                version.getQuestionText())

                                .displayOrder(
                                                quizQuestion.getDisplayOrder())

                                .build();
        }

        @Override
        public List<QuizResponse> getAllQuizzes() {

                List<Quiz> quizzes = quizDao.getAllQuizzes();

                return quizzes.stream()
                                .map(this::buildQuizResponse)
                                .toList();
        }

        @Override
        public QuizResponse getQuizByUuid(
                        UUID quizUuid) {

                Quiz quiz = quizDao.getQuizByUuid(
                                quizUuid);

                return buildQuizResponse(quiz);
        }

        private QuizResponse buildQuizResponse(
                        Quiz quiz) {

                List<QuizQuestion> quizQuestions = quizDao.getQuizQuestions(quiz);

                return QuizResponse.builder()

                                .quizUuid(quiz.getUuid())

                                .title(quiz.getTitle())

                                .questions(
                                                quizQuestions.stream()
                                                                .map(this::buildQuestionResponse)
                                                                .toList())

                                .build();
        }
}