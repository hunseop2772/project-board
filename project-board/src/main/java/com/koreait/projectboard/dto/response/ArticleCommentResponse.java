package com.koreait.projectboard.dto.response;

import com.koreait.projectboard.domain.Article;
import com.koreait.projectboard.domain.ArticleComment;
import com.koreait.projectboard.dto.ArticleCommentDto;
import com.koreait.projectboard.dto.ArticleDto;
import com.koreait.projectboard.dto.UserAccountDto;

import java.time.LocalDateTime;

public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickname

) {
    public static ArticleCommentResponse of(Long id, String content,
                                            LocalDateTime createdAt, String email,   String nickname ) {
        return new ArticleCommentResponse(id, content, createdAt, email, nickname);
    }

        public static  ArticleCommentResponse from(ArticleCommentDto dto){
            String nickname = dto.userAccountDto().nickname();
            if(nickname == null || nickname.isBlank()){
                nickname = dto.userAccountDto().userId();
            }

            return new ArticleCommentResponse(
                    dto.id(),
                    dto.content(),
                    dto.createdAt(),
                    dto.userAccountDto().email(),
                    nickname
            );
    }

}
