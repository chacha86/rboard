package com.example.reactboard.domain.article.article;

import com.example.reactboard.domain.article.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ArticleRestController {
    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<com.example.reactboard.domain.article.article.ArticlePageDto> getAllArticles(@RequestParam(defaultValue = "1") int page,
                                                                                                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                                                                       @RequestParam(value = "type", defaultValue = "total") String type) {
        Pageable pageable = Pageable.ofSize(10).withPage(page);
        ArticlePageDto articlePageDto = articleService.getAllArticles(type, keyword, pageable);
        return ResponseEntity.ok().body(articlePageDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleService.getArticle(id);
        return ResponseEntity.ok().body(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article updatedArticle = articleService.updateArticle(id, article.getTitle(), article.getContent());
        return ResponseEntity.ok().body(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
