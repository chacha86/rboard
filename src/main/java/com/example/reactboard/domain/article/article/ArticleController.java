package com.example.reactboard.domain.article.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<Page<Article>> getAllArticles(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                        @RequestParam(value = "type", defaultValue = "total") String type) {

        Pageable pageable = Pageable.ofSize(10).withPage(page);
        Page<Article> articlePage = articleService.getAllArticles(type, keyword, pageable);
        return ResponseEntity.ok().body(articlePage);
    }
}
