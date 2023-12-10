package com.barbearia.authentication.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.barbearia.authentication.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService 
{
    @Value("${jwt.secret.key}")
    private String key;

    public String generateToken(User user)
    {
        return JWT.create()
                .withIssuer("Authentication")
                .withSubject(user.getUsername())
                .withClaim("username", user.getPassword())
                .withExpiresAt(
                    LocalDateTime
                            .now()
                            .plusMinutes(20)
                            .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(key));
    }


    public String getSubject(String token)
    {
        return JWT.require(Algorithm.HMAC256(key))
                .withIssuer("Authentication")
                .build()
                .verify(token)
                .getSubject();
    }
    
}
