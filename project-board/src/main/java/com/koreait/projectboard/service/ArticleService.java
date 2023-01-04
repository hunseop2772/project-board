package com.koreait.projectboard.service;

import com.koreait.projectboard.domain.type.SearchType;
import com.koreait.projectboard.dto.ArticleDto;
import com.koreait.projectboard.dto.ArticleUpdateDto;
import com.koreait.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)// 데이터에 권한을 주지 않는다
    public Page<ArticleDto> searchArticles(SearchType title, String search_keyword){
        return Page.empty();
    }
    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long l){
        return null;
    }

    // 저장하는 곳, 일반적인 트렌젝션, 변하는 경우 데이터 권한을 주어야 하기 때문에 아무것도 안쓰는것이 맞다
    public void saveArticle(ArticleDto dto){

    }
    public void updateArticle(long articleId, ArticleUpdateDto dto){

    }
    public void deleteArticle(long articleId){

    }
}
