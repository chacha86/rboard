package com.example.reactboard.domain.article.article;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DslConfig {

    @Autowired
    private EntityManager em;

    @Bean
    public EntityManager test() {
        return this.em;
    }

//    public EntityManager test() {
//        return this.em;
//    }

    @Bean
    public Test test2() {
        return new Test();
    }
}
