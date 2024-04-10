package com.example.reactboard.domain.article.article;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Getter
public class TestRepository {

    @PersistenceContext
    private final EntityManager em;

    public List<Article> getArticles(List<String> targets, String kw1, String kw2) {
        JPAQuery<Article> query = new JPAQuery<>(em);
        QArticle article = QArticle.article;

        List<BooleanExpression> conditions = new ArrayList<>();
        if (targets.contains("title")) {
            conditions.add(article.title.contains(kw1));
        }
        if (targets.contains("content")) {
            conditions.add(article.content.contains(kw2));
        }

        BooleanExpression[] bools = conditions.toArray(new BooleanExpression[0]);

        return query.select(article)
                .from(article)
                .where(bools)
                .fetch();

    }

    public List<Article> getArticlesByMemberId(Long memberId) {
        System.out.println("member em = " + em);
        JPAQuery<Article> query = new JPAQuery<>(em);
        QArticle article = QArticle.article;

        return query.select(article)
                .from(article)
                .where(article.member.id.eq(memberId))
                .fetch();
    }

}
