package com.example.reactboard.domain.article.member;

import com.example.reactboard.domain.article.article.Article;
import com.example.reactboard.domain.article.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberInfo(@PathVariable("id") Long id) {
        Member member = memberService.getMemberInfo(id);
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Member> changeProfile(@PathVariable("id") Long id, @RequestParam("image") MultipartFile image) {
        System.out.println("hihihihi");
        Member member = memberService.getMemberInfo(id);
        memberService.updateImage(member, image);
        return ResponseEntity.ok().body(member);
    }
}
