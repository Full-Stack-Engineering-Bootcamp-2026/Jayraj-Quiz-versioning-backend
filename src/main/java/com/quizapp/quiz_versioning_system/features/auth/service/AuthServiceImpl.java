package com.quizapp.quiz_versioning_system.features.auth.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizapp.quiz_versioning_system.common.exception.BadRequestException;
import com.quizapp.quiz_versioning_system.features.auth.dao.AuthDao;
import com.quizapp.quiz_versioning_system.features.auth.dto.AuthResponse;
import com.quizapp.quiz_versioning_system.features.auth.dto.LoginRequest;
import com.quizapp.quiz_versioning_system.features.auth.dto.RegisterRequest;
import com.quizapp.quiz_versioning_system.features.permission.entity.Permission;
import com.quizapp.quiz_versioning_system.features.permission.enums.PermissionName;
import com.quizapp.quiz_versioning_system.features.user.entity.User;
import com.quizapp.quiz_versioning_system.security.jwt.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthDao authDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (authDao.existsByEmail(request.getEmail())) {

            throw new BadRequestException(
                    "Email already exists");
        }

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setIsActive(true);

        User savedUser = authDao.saveUser(user);

        List<String> permissionNames = List.of(
                PermissionName.QUIZ_ATTEMPT.name(),
                PermissionName.ATTEMPT_VIEW_OWN.name());

        List<Permission> permissions =
                authDao.getPermissionsByNames(
                        permissionNames);

        authDao.saveUserPermissions(
                savedUser,
                permissions);

        String token = jwtUtil.generateToken(
                savedUser.getUuid(),
                savedUser.getEmail(),
                permissionNames);

        return AuthResponse.builder()
                .userUuid(savedUser.getUuid())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .token(token)
                .permissions(permissionNames)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = authDao.getUserByEmail(
                request.getEmail());

        boolean isPasswordValid =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!isPasswordValid) {

            throw new BadRequestException(
                    "Invalid email or password");
        }

        List<String> permissions =
                user.getUserPermissions()
                        .stream()
                        .map(userPermission ->
                                userPermission
                                        .getPermission()
                                        .getName())
                        .toList();

        String token = jwtUtil.generateToken(
                user.getUuid(),
                user.getEmail(),
                permissions);

        return AuthResponse.builder()
                .userUuid(user.getUuid())
                .name(user.getName())
                .email(user.getEmail())
                .token(token)
                .permissions(permissions)
                .build();
    }
}