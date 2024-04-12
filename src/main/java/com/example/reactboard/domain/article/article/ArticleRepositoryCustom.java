package com.example.reactboard.domain.article.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {

    Page<Article> findMainArticles(String type, String kw, Pageable pageable);
}
