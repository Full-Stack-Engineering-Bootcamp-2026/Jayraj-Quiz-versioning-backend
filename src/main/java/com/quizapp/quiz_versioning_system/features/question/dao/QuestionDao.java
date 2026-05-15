package com.quizapp.quiz_versioning_system.features.question.dao;

import java.util.List;
import java.util.UUID;

import com.quizapp.quiz_versioning_system.features.question.entity.Question;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionOption;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

public interface QuestionDao {

        User getUserByUuid(UUID userUuid);

        Question saveQuestion(Question question);

        QuestionVersion saveQuestionVersion(
                        QuestionVersion questionVersion);

        List<QuestionOption> saveQuestionOptions(
                        List<QuestionOption> options);

        Question getQuestionByUuid(UUID questionUuid);

        QuestionVersion getLatestQuestionVersion(
                        Question question);

        List<Question> getAllQuestions();

        QuestionVersion getLatestQuestionVersionByQuestionUuid(
                        UUID questionUuid);
}