package com.example.reactboard.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;

    public String login(String id, String pass) {

        return jwtUtil.createToken(id);
    }
}
