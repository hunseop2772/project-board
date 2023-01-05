package com.koreait.projectboard.controller;

import com.koreait.projectboard.domain.type.SearchType;
import com.koreait.projectboard.dto.response.ArticleResponse;
import com.koreait.projectboard.dto.response.ArticleWithCommentResponse;
import com.koreait.projectboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor// 생성자 추가시 작성
@RequestMapping("articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;// 생성자

    @GetMapping
    public String articles(
            @RequestParam(required =false)SearchType searchType,
            @RequestParam(required =false)String searchValue,
            @PageableDefault(size = 10, sort = "createdAt",direction = Sort.Direction.DESC)Pageable pageable, ModelMap map
            ){

        map.addAttribute("articles", articleService
                .searchArticles(searchType,searchValue,pageable).map(ArticleResponse::from));
        return "articles/index"; // articles의 index 전달
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map){
        ArticleWithCommentResponse article = ArticleWithCommentResponse.from(articleService.getArticle(articleId));
        map.addAttribute("article", article);
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }
}