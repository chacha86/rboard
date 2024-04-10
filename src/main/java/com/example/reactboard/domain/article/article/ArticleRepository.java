package com.example.reactboard.domain.article.article;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.title LIKE %:keyword%")
    Page<Article> findAll(Pageable pageable, String keyword);

}
