package com.example.apisecurity.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

public class Jwt {

    @Getter
    private final String token;
    @Getter
    private final long userId;
    @Getter
    private final LocalDateTime issuedAt;
    @Getter
    private final LocalDateTime expiredAt;

    public static Jwt of(Long userId, long validityInMinutes, String securityKey) {
        var issuedDate = Instant.now();
        var expiration = issuedDate.plus(validityInMinutes, ChronoUnit.MINUTES);
        return new Jwt(
                Jwts.builder()
                        .claim("user_id", userId)
                        .setIssuedAt(Date.from(issuedDate))
                        .setExpiration(Date.from(expiration))
                        .signWith(SignatureAlgorithm.HS256,
                                Base64.getEncoder().encodeToString(securityKey.getBytes(StandardCharsets.UTF_8)))
                        .compact(),
                userId,
                LocalDateTime.ofInstant(issuedDate, ZoneId.systemDefault()),
                LocalDateTime.ofInstant(expiration, ZoneId.systemDefault())
        );
    }

    private Jwt(String token, long userId, LocalDateTime issuedAt, LocalDateTime expiredAt) {
        this.token = token;
        this.userId = userId;
        this.issuedAt = issuedAt;
        this.expiredAt = expiredAt;
    }
}
