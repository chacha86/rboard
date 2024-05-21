package com.example.reactboard.domain.article.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ArticlePageDto {
    List<ArticleDto> content = new ArrayList<>();
    int totalPages;
}
