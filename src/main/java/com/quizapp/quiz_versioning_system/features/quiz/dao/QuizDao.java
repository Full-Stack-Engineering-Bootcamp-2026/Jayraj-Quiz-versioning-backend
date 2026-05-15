package com.quizapp.quiz_versioning_system.features.quiz.dao;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.features.question.entity.Question;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.features.quiz.entity.Quiz;
import com.quizapp.quiz_versioning_system.features.quiz.entity.QuizQuestion;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

public interface QuizDao {

    User getUserByUuid(UUID userUuid);

    Question getQuestionByUuid(UUID questionUuid);

    QuestionVersion getLatestQuestionVersion(
            Question question);

    Quiz saveQuiz(Quiz quiz);

    List<QuizQuestion> saveQuizQuestions(
            List<QuizQuestion> quizQuestions);
}