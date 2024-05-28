package com.example.reactboard.domain.auth.filter;

import com.example.reactboard.domain.article.member.Member;
import com.example.reactboard.domain.article.member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class LoginFilter implements Filter {

    private final MemberService memberService;

    private static final List<String> EXCLUDE_URLS = Arrays.asList(
            "/api/v1/auth/success",
            "/api/v1/auth/fail"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        String path = httpReq.getRequestURI();
        if (EXCLUDE_URLS.stream().anyMatch(path::startsWith)) {
            chain.doFilter(request, response);
            return;
        }
        if(!httpReq.getMethod().equals("POST")) {
            httpResp.sendRedirect("/api/v1/auth/fail");
            return;
        }

        try {
            ServletInputStream stream = httpReq.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> loginParam = objectMapper.readValue(stream, Map.class);

            System.out.println(loginParam.get("loginId"));
            Member member = memberService.getMember(loginParam.get("loginId"));
            System.out.println(member.getPassword());
            if (member.getPassword().equals(loginParam.get("loginPw"))) {
                System.out.println("로그인 성공");
                httpResp.sendRedirect("/api/v1/auth/success");
                return;
            }
        } catch (Exception e) {
            httpResp.sendRedirect("/api/v1/auth/fail");
            return;
        }

        chain.doFilter(request, response);
    }
}
