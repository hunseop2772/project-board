package com.koreait.projectboard.domain;

import com.koreait.projectboard.config.JpaConfig;
import com.koreait.projectboard.repository.ArticleCommentRepository;
import com.koreait.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Disabled("JPA  Test는 불팔요하므로 제외시킴")
@Import(JpaConfig.class)
@DisplayName("JPA 연결 테스트")
@DataJpaTest
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ){
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void select(){
        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isNotNull().hasSize(1000);

    }

    @DisplayName("insert test")
    @Test
    void insert(){
        long prevCount = articleRepository.count();
        Article saveArticle = articleRepository.save(Article.of("새로운 글","새로운 글을 등록합니다","#new"));
                //Article.of : 내용 집어넣기
        assertThat(articleRepository.count()).isEqualTo(prevCount+1);
        // 이전 데이터보다 늘어났으면 통과 한다는 의미이다. 추가한 경우 늘어나야 하기 때문이다. 비교하는 것을 의미(assertThat)
    }

    @DisplayName("update test")
    @Test
    void update(){
        Article article =articleRepository.findById(1l).orElseThrow();
        //orElseThrow() ; exception을 던짐 찾는것이 없으면
        String updateHashTag = "#Spring";
        article.setHashtag(updateHashTag);

        Article savedArticle = articleRepository.saveAndFlush(article);// 재 시작 시 드랍되어 보지 못하는 경우 플러시로 확인가능하게
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updateHashTag ); // 내가 업데이트 한것이 맞는지
    }

    @DisplayName("delete test")
    @Test
    void delete(){
        Article article = articleRepository.findById(1l).orElseThrow();
        long preArticleCount = articleRepository.count(); // 1000개 가 들어왔는지 확인
        long preArticleCommentCount = articleCommentRepository.count(); // 댓글 수를 확인
        int deletedCommentsSize = article.getArticleComments().size();// 댁글 수

        articleRepository.delete(article); // 원본 글을 삭제

        assertThat(articleRepository.count()).isEqualTo(preArticleCount -1); // 삭제니 -1인지 확인
        assertThat(articleCommentRepository.count()).isEqualTo(preArticleCommentCount -deletedCommentsSize);
        //뺀 값과 같은지 확인을 해봐야 하니 빼서 값이 맞는지 확인을 해야 하낟.
    }
}