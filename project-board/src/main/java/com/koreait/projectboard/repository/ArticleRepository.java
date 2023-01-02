package com.koreait.projectboard.repository;

import com.koreait.projectboard.domain.Article;
import com.koreait.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource//내가 설정한 REST 기능을 부여함(기본적인 API 자동 생성)
public interface ArticleRepository extends

        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // like검색이 아닌 전체단어 일치 검색

// 쿼리에 적용하고자 하는 엔티티, 기본기능 중 완벽하게 동일한 것만 구분 가능(like는 아님)
        QuerydslBinderCustomizer<QArticle>  // like검색을 위해 오버라이드 필요함

{

       @Override
       default void customize(QuerydslBindings bindings, QArticle root){   //추상메소드 안에 적는 법 =default
              bindings.excludeUnlistedProperties(true);
              bindings.including(root.title, root.content, root.hashtag  ,root.createdAt, root.createdBy);
              bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  //like 검색이 된다.
              bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
              bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
              bindings.bind(root.createdAt).first(DateTimeExpression::eq);
              bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

       }
}