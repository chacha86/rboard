package com.example.reactboard.domain.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    public String createToken(String id) {;
        SecretKey key = Jwts.SIG.HS256.key().build();
        String jws = Jwts.builder().subject(id).signWith(key).compact();
        return jws;
    }
}
