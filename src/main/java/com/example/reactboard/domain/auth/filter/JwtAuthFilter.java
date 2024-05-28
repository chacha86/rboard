package com.example.reactboard.domain.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JwtAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        // todo1. authorization 헤더가 있는지 확인
        // todo2. 인증 기능이 아니면 걍 넘김 -> 다음 필터로 넘김(url 패턴으로 구분)
        // todo3. 인증 기능이면 인증 체크 -> 토큰 유효성 검사
        // todo4. 토큰이 없으면 -> 예외
        String accessToken = getAccessTokenOrNull(request);
        System.out.println("123");

        if (accessToken == null) {
            httpResp.sendRedirect("/api/v1/auth/fail?code=%s&msg=%s".formatted("F-403", URLEncoder.encode("엑세스 토큰이 없습니다.", StandardCharsets.UTF_8)));
            return;
        }

        System.out.println("456");
        chain.doFilter(request, response);
        // todo6. 토큰이 있으면 -> 토큰에서 회원 정보 추출

        // todo7. 토큰이 있으나 만료됐으면 -> 리프레쉬 토큰으로 토큰 재발급 -> 회원 정보 추출
        // todo8. 추출한 회원 정보를 SecurityContext에 저장
        // todo9. 다음 필터로 넘김


    }

    private void refreshToken() {
    }

    private String getAccessTokenOrNull(ServletRequest req) {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        final String TOKEN_TYPE_PREFIX = "Bearer ";
        final int TOKEN_TYPE_PREFIX_LENGTH = TOKEN_TYPE_PREFIX.length();

        String authorization = httpReq.getHeader("Authorization");
        if (authorization == null) {
            System.out.println("Authorization 헤더가 없습니다.");
            return null;
        }

        if (!authorization.startsWith(TOKEN_TYPE_PREFIX)) {
            System.out.println("Bearer 토큰이 아닙니다.");
            return null;
        }

        return authorization.substring(TOKEN_TYPE_PREFIX_LENGTH);
    }
}
