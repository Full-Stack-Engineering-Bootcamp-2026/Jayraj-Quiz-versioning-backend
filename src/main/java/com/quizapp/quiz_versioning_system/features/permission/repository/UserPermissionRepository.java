package com.quizapp.quiz_versioning_system.features.permission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quiz_versioning_system.features.permission.entity.UserPermission;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {

    List<UserPermission> findByUser(User user);
}