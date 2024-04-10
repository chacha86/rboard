package com.example.reactboard.domain.article.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(String username) {
        Member member = new Member();
        member.setUsername(username);
        return memberRepository.save(member);
    }

}
