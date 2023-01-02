package com.koreait.projectboard.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data REST - API TEST")
@AutoConfigureMockMvc // MockMvc 사용하기 위해
@Transactional // 실제로 운영하느 서버가 있으면 테스트와 운영 서버를 분리해서
// 실행하여 부하를 줄여주고 데이터가 추가되거나 변경되더라도 롤벡 기능이 있어 오류가 줄어든다
@SpringBootTest
class ArticleTest {
    private final MockMvc mvc;

    public ArticleTest(@Autowired MockMvc mvc){ // MockMvc 기능을 주입시켰다.
        this.mvc = mvc;
    }

    @DisplayName("[API] Board List Join" )
    @Test
    void read() throws Exception{
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
        // get 방식으로 받아오고 그 다음에 실행하는 and.... 스테이터스가 잘 들어오면 문제없지만 에러가 발생하면 에러를 발생시킨다
        .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[API] Board 단건 Join" )
    @Test // /api/articles/1
    void detail() throws Exception {
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk()) // 404가 아닌 ok가 나오는지
                // get 방식으로 받아오고 그 다음에 실행하는 and.... 스테이터스가 잘 들어오면 문제없지만 에러가 발생하면 에러를 발생시킨다
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[API] 게시글 -> 댓글 리스트 조회" )
    @Test // /api/articles/1/articleComments
    void detailComments() throws Exception {
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                // get 방식으로 받아오고 그 다음에 실행하는 and.... 스테이터스가 잘 들어오면 문제없지만 에러가 발생하면 에러를 발생시킨다
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
    @DisplayName("[API] 댓글 리스트 조회" )
    @Test // /api/articleComments
    void readComments() throws Exception {
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                // get 방식으로 받아오고 그 다음에 실행하는 and.... 스테이터스가 잘 들어오면 문제없지만 에러가 발생하면 에러를 발생시킨다
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

//    @DisplayName("[API] 댓글 단건 조회" )
//    @Test // /api/articleComments/1
//    void detailComment() throws Exception {
//        mvc.perform(get("/api/articleComments/1"))
//                .andExpect(status().isOk())
//                // get 방식으로 받아오고 그 다음에 실행하는 and.... 스테이터스가 잘 들어오면 문제없지만 에러가 발생하면 에러를 발생시킨다
//                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
//    }
}