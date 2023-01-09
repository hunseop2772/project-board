package com.koreait.projectboard.service;

import com.koreait.projectboard.domain.Article;
import com.koreait.projectboard.domain.type.SearchType;
import com.koreait.projectboard.dto.ArticleDto;
import com.koreait.projectboard.dto.ArticleWithCommentsDto;
import com.koreait.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)// 데이터에 권한을 주지 않는다
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable){
        if (searchKeyword == null || searchKeyword.isBlank()){
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch (searchType){
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NickNameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag(searchKeyword, pageable).map(ArticleDto::from);
        };
    }
    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId){
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId : " + articleId));
    }

    // 저장하는 곳, 일반적인 트렌젝션, 변하는 경우 데이터 권한을 주어야 하기 때문에 아무것도 안쓰는것이 맞다
    public void saveArticle(ArticleDto dto){
        articleRepository.save(dto.toEntity());
    }
    public void updateArticle(ArticleDto dto) {// 바로 세이브 하는것이 아니라 있는지 없는지 확인을 하고 업데이트를 해야한다. getReferenceById : 셀렉트 없이 실행
        try {
            Article article = articleRepository.getReferenceById(dto.id());
            if (dto.title() != null) {
                article.setTitle(dto.title());
            }
            if (dto.content() != null) {
                article.setContent(dto.content());
            }
            article.setHashtag(dto.hashtag());
            // dto.title() 없으면 get ()안에 머가 있으면 set -> 자바17버전
        }catch (EntityNotFoundException e){
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없음 - dto: {}", dto);
        }
    }
    public void deleteArticle(Long articleId){
        articleRepository.deleteById(articleId);
    }

    // Via : ~을 통해서 라는 의미로 해석하자
   @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticleViaHashtag(String hashtag, Pageable pageable){
        if (hashtag ==null || hashtag.isBlank()){
            return  Page.empty(pageable);
        }
        return  articleRepository.findByHashtag(hashtag, pageable).map(ArticleDto::from);
    }

    public long getArticleCount(){
        return articleRepository.count();
    }

    public List<String> getHashtags(){
        return articleRepository.findAllDistinctHashtags();
    }
}
