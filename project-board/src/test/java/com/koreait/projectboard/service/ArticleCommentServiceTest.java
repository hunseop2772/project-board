package com.koreait.projectboard.service;

import com.koreait.projectboard.domain.type.SearchType;
import com.koreait.projectboard.dto.ArticleCommentDto;
import com.koreait.projectboard.repository.ArticleCommentRepository;
import com.koreait.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
    @InjectMocks private ArticleCommentService sut;
    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;

    @DisplayName("")
    @Test
    void list(){
    }
}