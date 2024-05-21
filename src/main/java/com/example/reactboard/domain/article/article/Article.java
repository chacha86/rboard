package com.example.reactboard.domain.article.article;

import com.example.reactboard.domain.article.member.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public ArticleDto toArticleDto() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(id);
        articleDto.setTitle(title);
        articleDto.setContent(content);
        articleDto.setMember(member);
        return articleDto;
    }
}
