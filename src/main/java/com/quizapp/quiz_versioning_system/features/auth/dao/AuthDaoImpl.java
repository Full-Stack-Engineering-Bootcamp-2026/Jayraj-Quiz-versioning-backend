package com.quizapp.quiz_versioning_system.features.auth.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.quiz_versioning_system.common.exception.ResourceNotFoundException;

import com.quizapp.quiz_versioning_system.features.permission.entity.Permission;
import com.quizapp.quiz_versioning_system.features.permission.entity.UserPermission;
import com.quizapp.quiz_versioning_system.features.permission.repository.PermissionRepository;
import com.quizapp.quiz_versioning_system.features.permission.repository.UserPermissionRepository;
import com.quizapp.quiz_versioning_system.features.user.entity.User;
import com.quizapp.quiz_versioning_system.features.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AuthDaoImpl implements AuthDao {

    private final UserRepository userRepository;

    private final PermissionRepository permissionRepository;

    private final UserPermissionRepository userPermissionRepository;

    @Override
    @Transactional
    public User saveUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<Permission> getPermissionsByNames(List<String> names) {

        List<Permission> permissions = new ArrayList<>();

        for (String name : names) {

            Permission permission = permissionRepository
                    .findByName(name)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Permission not found: " + name));

            permissions.add(permission);
        }

        return permissions;
    }

    @Override
    @Transactional
    public void saveUserPermissions(
            User user,
            List<Permission> permissions) {

        List<UserPermission> userPermissions =
                permissions.stream()
                        .map(permission -> {

                            UserPermission userPermission =
                                    new UserPermission();

                            userPermission.setUser(user);

                            userPermission.setPermission(permission);

                            return userPermission;

                        }).toList();

        userPermissionRepository.saveAll(userPermissions);
    }
}