package com.koreait.projectboard.repository;

import com.koreait.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource//내가 설정한 REST 기능을 부여함(기본적인 API 자동 생성)
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
