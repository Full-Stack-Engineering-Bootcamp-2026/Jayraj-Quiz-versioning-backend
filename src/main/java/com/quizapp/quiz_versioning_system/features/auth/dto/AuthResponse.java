package com.quizapp.quiz_versioning_system.features.auth.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

    private UUID userUuid;

    private String name;

    private String email;

    private String token;

    private List<String> permissions;
}