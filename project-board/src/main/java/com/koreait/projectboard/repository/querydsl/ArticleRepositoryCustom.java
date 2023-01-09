package com.koreait.projectboard.repository.querydsl;

import java.util.List;


// 위 내용을 구현해 줄 클래스가 필요하다 -> 일단 사용은 ArticleRepository에서 사용을 한다.
// 이런 클래스를 만들기 위해서는 동일 이름에 Impl 추가 동일 패키지에
public interface ArticleRepositoryCustom {
    List<String> findAllDistinctHashtags();

}
