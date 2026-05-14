package com.quizapp.quiz_versioning_system.features.question.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.quiz_versioning_system.common.exception.ResourceNotFoundException;
import com.quizapp.quiz_versioning_system.features.question.entity.Question;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionOption;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.features.question.repository.QuestionOptionRepository;
import com.quizapp.quiz_versioning_system.features.question.repository.QuestionRepository;
import com.quizapp.quiz_versioning_system.features.question.repository.QuestionVersionRepository;
import com.quizapp.quiz_versioning_system.features.user.entity.User;
import com.quizapp.quiz_versioning_system.features.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {

    private final UserRepository userRepository;

    private final QuestionRepository questionRepository;

    private final QuestionVersionRepository
            questionVersionRepository;

    private final QuestionOptionRepository
            questionOptionRepository;

    @Override
    public User getUserByUuid(UUID userUuid) {

        return userRepository.findByUuid(userUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));
    }

    @Override
    @Transactional
    public Question saveQuestion(Question question) {

        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public QuestionVersion saveQuestionVersion(
            QuestionVersion questionVersion) {

        return questionVersionRepository
                .save(questionVersion);
    }

    @Override
    @Transactional
    public List<QuestionOption> saveQuestionOptions(
            List<QuestionOption> options) {

        return questionOptionRepository.saveAll(options);
    }

    @Override
    public Question getQuestionByUuid(UUID questionUuid) {

        return questionRepository.findByUuid(questionUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question not found"));
    }

    @Override
    public QuestionVersion getLatestQuestionVersion(
            Question question) {

        return questionVersionRepository
                .findTopByQuestionOrderByVersionNumberDesc(
                        question)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question version not found"));
    }
}