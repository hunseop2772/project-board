package com.koreait.projectboard.repository;

import com.koreait.projectboard.domain.Article;
import com.koreait.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        QuerydslBinderCustomizer<QArticle> { // like검색을 위해 오버라이드 필요함

       //pageable : 한번에 가저올 수를 의미한다.
       Page<Article> findByTitleContaining(String title, Pageable pageable);
       //findByTitleContaining : 정화한 타이틀을 찾는것이 아닌 일부만으로 찾을 수 있다.
       Page<Article> findByContentContaining(String title, Pageable pageable);
       Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
       // UserAccount와 같은 객체를 찾을 경우에는 _ Userid를 이용하여 찾는다.
       Page<Article> findByUserAccount_NickNameContaining(String nickname, Pageable pageable);
       Page<Article> findByHashtag(String hashtag, Pageable pageable);



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