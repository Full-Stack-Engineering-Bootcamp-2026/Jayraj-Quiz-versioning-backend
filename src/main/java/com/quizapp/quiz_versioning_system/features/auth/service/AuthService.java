package com.quizapp.quiz_versioning_system.features.auth.service;

import com.quizapp.quiz_versioning_system.features.auth.dto.AuthResponse;
import com.quizapp.quiz_versioning_system.features.auth.dto.LoginRequest;
import com.quizapp.quiz_versioning_system.features.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}