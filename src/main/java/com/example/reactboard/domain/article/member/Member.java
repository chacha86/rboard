package com.example.reactboard.domain.article.member;

import com.example.reactboard.domain.article.article.Article;
import com.example.reactboard.domain.article.member.image.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "member")
    private List<Article> articles;
}
