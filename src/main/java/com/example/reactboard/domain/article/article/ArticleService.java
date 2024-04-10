package com.example.reactboard.domain.article.article;

import com.example.reactboard.domain.article.member.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;

        save("제목1", "내용1");
        save("제목2", "내용2");
        save("제목3", "내용3");
    }

    @Transactional
    public Article save(String title, String content, Member member) {
        Article article = save(title, content);
        article.setMember(member);
        return article;
    }

    public Article save(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        return articleRepository.save(article);
    }

    public Article getArticle(long id) {
        return articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    public void deleteArticle(long id) {
        articleRepository.deleteById(id);
    }

    public Article updateArticle(long id, String title, String content) {
        Article article = getArticle(id);
        article.setTitle(title);
        article.setContent(content);
        return articleRepository.save(article);
    }

    public Page<Article> getAllArticles(Pageable pageable, String keyword) {
        return articleRepository.findAll(pageable, keyword);
    }
}
