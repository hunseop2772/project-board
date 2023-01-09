package com.koreait.projectboard.repository.querydsl;

import com.koreait.projectboard.domain.Article;
import com.koreait.projectboard.domain.QArticle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements  ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class); //사용하고자 하는 엔티티 클래스에 보낸다
    }


    //from() 셀렉트의 프롬과 동일하다 -> 어디로 부터 select distinct(*) from article where hashtag is not null;
    //fetch() 사용하여 위의 내용은 스트링의 리스트 형으로 변환된다.
    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;
        return from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();

    }
}
