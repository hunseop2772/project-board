package com.koreait.projectboard.service;

import com.koreait.projectboard.dto.ArticleCommentDto;
import com.koreait.projectboard.repository.ArticleCommentRepository;
import com.koreait.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ArticleCommentService {
private final ArticleRepository articleRepository;
private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId){
        return List.of();
    }


    public void saveArticleComment(ArticleCommentDto dto){

    }
    public void updateArticleComment(ArticleCommentDto dto){

    }

    public void deleteArticleComment(Long articleCommentId){

    }

}
