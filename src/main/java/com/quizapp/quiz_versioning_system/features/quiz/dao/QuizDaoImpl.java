package com.quizapp.quiz_versioning_system.features.quiz.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.quiz_versioning_system.common.exception.ResourceNotFoundException;
import com.quizapp.quiz_versioning_system.features.question.entity.Question;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.features.question.repository.QuestionRepository;
import com.quizapp.quiz_versioning_system.features.question.repository.QuestionVersionRepository;
import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;
import com.quizapp.quiz_versioning_system.features.quiz.entity.QuizQuestion;
import com.quizapp.quiz_versioning_system.features.quiz.repository.QuizQuestionRepository;
import com.quizapp.quiz_versioning_system.features.quiz.repository.QuizRepository;
import com.quizapp.quiz_versioning_system.features.user.entity.User;
import com.quizapp.quiz_versioning_system.features.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuizDaoImpl implements QuizDao {

        private final UserRepository userRepository;

        private final QuestionRepository questionRepository;

        private final QuestionVersionRepository questionVersionRepository;

        private final QuizRepository quizRepository;

        private final QuizQuestionRepository quizQuestionRepository;

        @Override
        public User getUserByUuid(UUID userUuid) {

                return userRepository.findByUuid(userUuid)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "User not found"));
        }

        @Override
        public Question getQuestionByUuid(UUID questionUuid) {

                return questionRepository.findByUuid(
                                questionUuid)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Question not found"));
        }

        @Override
        public QuestionVersion getLatestQuestionVersion(
                        Question question) {

                return questionVersionRepository
                                .findTopByQuestionOrderByVersionNumberDesc(
                                                question)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Question version not found"));
        }

        @Override
        @Transactional
        public Quiz saveQuiz(Quiz quiz) {

                return quizRepository.save(quiz);
        }

        @Override
        @Transactional
        public List<QuizQuestion> saveQuizQuestions(
                        List<QuizQuestion> quizQuestions) {

                return quizQuestionRepository
                                .saveAll(quizQuestions);
        }

        @Override
        public List<Quiz> getAllQuizzes() {

                return quizRepository.findAll()
                                .stream()
                                .filter(Quiz::getIsActive)
                                .toList();
        }

        @Override
        public Quiz getQuizByUuid(UUID quizUuid) {

                return quizRepository.findByUuid(quizUuid)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Quiz not found"));
        }

        @Override
        public List<QuizQuestion> getQuizQuestions(
                        Quiz quiz) {

                return quizQuestionRepository
                                .findByQuizOrderByDisplayOrder(
                                                quiz);
        }
}