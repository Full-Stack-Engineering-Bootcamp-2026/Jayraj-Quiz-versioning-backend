package com.quizapp.quiz_versioning_system.features.auth.dao;

import java.util.List;

import com.quizapp.quiz_versioning_system.features.permission.entity.Permission;
import com.quizapp.quiz_versioning_system.features.user.entity.User;

public interface AuthDao {

    User saveUser(User user);

    boolean existsByEmail(String email);

    User getUserByEmail(String email);

    List<Permission> getPermissionsByNames(List<String> names);

    void saveUserPermissions(User user, List<Permission> permissions);
}