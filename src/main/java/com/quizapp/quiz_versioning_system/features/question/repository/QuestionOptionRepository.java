package com.quizapp.quiz_versioning_system.features.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.question.entity.QuestionOption;
import com.quizapp.quiz_versioning_system.features.question.entity.QuestionVersion;

public interface QuestionOptionRepository
                extends JpaRepository<QuestionOption, Long> {

        List<QuestionOption> findByQuestionVersionOrderByDisplayOrder(
                        QuestionVersion questionVersion);
}