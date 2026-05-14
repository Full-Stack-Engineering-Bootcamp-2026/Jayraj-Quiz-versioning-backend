package com.quizapp.quiz_versioning_system.security.util;

import java.text.ParseException;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWTClaimsSet;

@Component
public class SecurityUtil {

    public UUID getCurrentUserUuid() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        JWTClaimsSet claims =
                (JWTClaimsSet) authentication.getPrincipal();

        try {

            return UUID.fromString(
                    claims.getStringClaim("userUuid"));

        } catch (ParseException e) {

            throw new RuntimeException(
                    "Invalid user UUID in token");
        }
    }

    public String getCurrentUserEmail() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        JWTClaimsSet claims =
                (JWTClaimsSet) authentication.getPrincipal();

        return claims.getSubject();
    }
}