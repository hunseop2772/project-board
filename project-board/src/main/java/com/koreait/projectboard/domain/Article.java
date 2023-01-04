package com.koreait.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@ToString(callSuper = true)// 자기거라 부모것도 불러와라
@Table(indexes = { //@Table은 엔티티와 매핑할 테이블을 지정
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
        // name을 추가하면 테이블이름이 name값으로 설정이 되고 생략시 Entity이름으로 테이블이 만들어지는 것을 확인할 수 있다.
})
@Entity
//@Entity 어노테이션은 JPA를 사용해 테이블과 매핑할 클래스에 붙여주는 어노테이션이다. 이 어노테이션을 붙임으로써 JPA가 해당 클래스를 관리하게 된다.
public class Article extends AuditingFields{
    @Id// Entity 입력할 경우 프라이머리 키를 선언해 줘야한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임 (Mysql)
    private Long id;
    @Setter @ManyToOne(optional = false) private UserAccount userAccount; // 유저정보,  알티클은 많이 들어가고 유저는 하나만 들어가야 한다.
    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문
    @Setter private String hashtag; // 해시태그


    //JPA에서 DB Table의 Column을 Mapping 할 때 @Column Annotation을 사용한다.

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    protected Article() {}

    private Article(UserAccount userAccount, String title, String content, String hashtag) {

        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(UserAccount userAccount,String title, String content, String hashtag) { // of함수 객체를 만들 수 있는 함수
        return new Article(userAccount, title, content, hashtag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }
}