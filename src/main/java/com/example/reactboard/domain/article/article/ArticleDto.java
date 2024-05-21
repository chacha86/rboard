package com.example.reactboard.domain.article.article;

import com.example.reactboard.domain.article.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private Member member;
}
