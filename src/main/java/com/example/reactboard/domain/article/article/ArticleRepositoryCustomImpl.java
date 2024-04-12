package com.example.reactboard.domain.article.article;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Article> findMainArticles(String type, String kw, Pageable pageable) {
        assert type != null;
        assert kw != null;
        assert pageable != null;

        QArticle article = QArticle.article;

        Map<String, BooleanExpression> expressionMap = Map.of(
                "title", article.title.like("%" + kw + "%"),
                "content", article.content.like("%" + kw + "%"),
                "author", article.member.username.like("%" + kw + "%")
        );

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(expressionMap.get(type));

        if (type.equals("total")) {
            BooleanBuilder totalBuilder = new BooleanBuilder();

            for (BooleanExpression expression : expressionMap.values()) {
                totalBuilder.or(expression);
            }

            builder = totalBuilder;
        }

        List<Article> query = queryFactory
                .selectFrom(article)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(article.count())
                .from(article)
                .where(builder);

        return PageableExecutionUtils.getPage(query, pageable, count::fetchOne);
    }
}
