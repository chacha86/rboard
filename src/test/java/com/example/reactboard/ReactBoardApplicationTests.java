package com.example.reactboard;

import com.example.reactboard.domain.article.article.Article;
import com.example.reactboard.domain.article.article.ArticleService;
import com.example.reactboard.domain.article.article.QArticle;
import com.example.reactboard.domain.article.article.TestRepository;
import com.example.reactboard.domain.article.member.Member;
import com.example.reactboard.domain.article.member.MemberService;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@SpringBootTest
class ReactBoardApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("테스트 데이터 생성")
    @Transactional
    @Rollback(value = false)
    void t1() {
        Random random = new Random();
        String[] fruits = {"바나나", "사과", "딸기", "블루베리"};
        Member m1 = memberService.save("김철수");
        Member m2 = memberService.save("홍길동");
        Member m3 = memberService.save("이순신");

        Member[] members = {m1, m2, m3};

        for (int i = 0; i < 100; i++) {
            Member member = members[random.nextInt(3)];
            String str = fruits[random.nextInt(4)] + "_" + i;
            articleService.save(str, str, member);
        }


    }

    @Autowired
    private TestRepository testRepository;

    @Test
    void t2() {
        List<String> targets = List.of("title", "content");
        List<Article> articles = testRepository.getArticles(targets, "바나나", "바나나");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    @DirtiesContext
    void t3() {
    }

    @Test
    void t4() {
    }
}
