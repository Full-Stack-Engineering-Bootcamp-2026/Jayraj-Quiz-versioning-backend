package com.quizapp.quiz_versioning_system.features.question.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.exception.BadRequestException;
import com.quizapp.quiz_versioning_system.features.question.dao.QuestionDao;
import com.quizapp.quiz_versioning_system.features.question.dto.CreateQuestionRequest;
import com.quizapp.quiz_versioning_system.features.question.dto.QuestionOptionRequest;
import com.quizapp.quiz_versioning_system.features.question.dto.QuestionOptionResponse;
import com.quizapp.quiz_versioning_system.features.question.dto.QuestionResponse;
import com.quizapp.quiz_versioning_system.features.question.entity.Question;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionOption;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;
import com.quizapp.quiz_versioning_system.features.question.enums.QuestionType;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    @Override
    public QuestionResponse createQuestion(
            CreateQuestionRequest request,
            UUID userUuid) {

        validateQuestionRequest(request);

        User user = questionDao.getUserByUuid(userUuid);

        Question question = new Question();

        question.setCreatedBy(user);

        Question savedQuestion = questionDao.saveQuestion(question);

        QuestionVersion questionVersion = new QuestionVersion();

        questionVersion.setQuestion(savedQuestion);

        questionVersion.setVersionNumber(1);

        questionVersion.setQuestionText(
                request.getQuestionText());

        questionVersion.setQuestionType(
                request.getQuestionType());

        questionVersion.setCreatedBy(user);

        QuestionVersion savedVersion = questionDao.saveQuestionVersion(
                questionVersion);

        List<QuestionOption> savedOptions = saveQuestionOptions(
                request.getOptions(),
                savedVersion);

        return buildQuestionResponse(
                savedQuestion,
                savedVersion,
                savedOptions);
    }

    @Override
    public QuestionResponse editQuestion(
            UUID questionUuid,
            CreateQuestionRequest request,
            UUID userUuid) {

        validateQuestionRequest(request);

        User user = questionDao.getUserByUuid(userUuid);

        Question question = questionDao.getQuestionByUuid(
                questionUuid);

        QuestionVersion latestVersion = questionDao.getLatestQuestionVersion(
                question);

        Integer nextVersionNumber = latestVersion.getVersionNumber() + 1;

        QuestionVersion newVersion = new QuestionVersion();

        newVersion.setQuestion(question);

        newVersion.setVersionNumber(
                nextVersionNumber);

        newVersion.setQuestionText(
                request.getQuestionText());

        newVersion.setQuestionType(
                request.getQuestionType());

        newVersion.setCreatedBy(user);

        QuestionVersion savedVersion = questionDao.saveQuestionVersion(
                newVersion);

        List<QuestionOption> savedOptions = saveQuestionOptions(
                request.getOptions(),
                savedVersion);

        return buildQuestionResponse(
                question,
                savedVersion,
                savedOptions);
    }

    private void validateQuestionRequest(
            CreateQuestionRequest request) {

        if (request.getQuestionType() == QuestionType.TEXT) {

            return;
        }

        if (request.getOptions() == null
                || request.getOptions().isEmpty()) {

            throw new BadRequestException(
                    "Options are required for objective questions");
        }
    }

    private List<QuestionOption> saveQuestionOptions(
            List<QuestionOptionRequest> optionRequests,
            QuestionVersion questionVersion) {

        if (optionRequests == null
                || optionRequests.isEmpty()) {

            return List.of();
        }

        List<QuestionOption> options = optionRequests.stream()
                .map(optionRequest -> {

                    QuestionOption option = new QuestionOption();

                    option.setQuestionVersion(
                            questionVersion);

                    option.setOptionText(
                            optionRequest.getOptionText());

                    option.setDisplayOrder(
                            optionRequest.getDisplayOrder());

                    return option;

                }).toList();

        return questionDao.saveQuestionOptions(
                options);
    }

    private QuestionResponse buildQuestionResponse(
            Question question,
            QuestionVersion questionVersion,
            List<QuestionOption> options) {

        return QuestionResponse.builder()

                .questionUuid(question.getUuid())

                .questionVersionUuid(
                        questionVersion.getUuid())

                .versionNumber(
                        questionVersion.getVersionNumber())

                .questionText(
                        questionVersion.getQuestionText())

                .questionType(
                        questionVersion.getQuestionType())

                .options(
                        options.stream()
                                .map(option -> QuestionOptionResponse
                                        .builder()
                                        .optionUuid(
                                                option.getUuid())
                                        .optionText(
                                                option.getOptionText())
                                        .displayOrder(
                                                option.getDisplayOrder())
                                        .build())
                                .toList())

                .build();
    }
}