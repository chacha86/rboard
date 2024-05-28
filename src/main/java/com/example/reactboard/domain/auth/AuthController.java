package com.example.reactboard.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @GetMapping("/success")
    public ResultData login(String id, String password) {
        System.out.println("success");
        return new ResultData("S-200", "로그인 성공", null);

    }

    @GetMapping("/fail")
    public ResultData fail() {
        System.out.println("fail");
        return new ResultData("F-401", "로그인 실패", null);
    }
}
