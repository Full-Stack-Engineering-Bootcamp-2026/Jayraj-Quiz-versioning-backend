package com.quizapp.quiz_versioning_system.security.jwt;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.quizapp.quiz_versioning_system.common.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public String generateToken(
            UUID userUuid,
            String email,
            List<String> permissions) {

        try {

            JWSSigner signer = new MACSigner(jwtConfig.getSecret());

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(email)
                    .claim("userUuid", userUuid.toString())
                    .claim("permissions", permissions)
                    .issueTime(new Date())
                    .expirationTime(
                            new Date(
                                    System.currentTimeMillis()
                                            + jwtConfig.getExpiration()))
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet);

            signedJWT.sign(signer);

            return signedJWT.serialize();

        } catch (JOSEException e) {

            throw new RuntimeException("Failed to generate JWT token");
        }
    }

    public JWTClaimsSet validateToken(String token) {

        try {

            SignedJWT signedJWT = SignedJWT.parse(token);

            JWSVerifier verifier = new MACVerifier(
                    jwtConfig.getSecret().getBytes());

            if (!signedJWT.verify(verifier)) {
                throw new UnauthorizedException("Invalid token");
            }

            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            if (claims.getExpirationTime().before(new Date())) {
                throw new UnauthorizedException("Token expired");
            }

            return claims;

        } catch (ParseException | JOSEException e) {

            throw new UnauthorizedException("Invalid token");
        }
    }
}