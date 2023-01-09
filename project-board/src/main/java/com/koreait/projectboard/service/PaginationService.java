package com.koreait.projectboard.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private static final  int BAR_LENGTH =10;

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages){
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH /2),0); // 시작을 0으로
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);
        return IntStream.range(startNumber, endNumber).boxed().toList();
        //1,10까지 이면 그 사이 범위를 정수형으로 만들어 주는 기능이 인트 스트림이다.
        // boxed() : 래퍼 클래스로 변경 Integer 형으로 변경
        // toList : List 타입으로 변경 최초에 List<Integer> 형으로 사용을 하였기 떄문이다.
    }
    public int currentBarLength(){
        return BAR_LENGTH;
    }
}
