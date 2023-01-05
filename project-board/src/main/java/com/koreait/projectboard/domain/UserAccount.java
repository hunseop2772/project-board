package com.koreait.projectboard.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "userid"),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields{
    // 테이블은 user, account는 만들지 말자 기본 변수로 선택된 경우도 있어 많은 오류를 발생시키기 때문이다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 50)
    private String userId;

    @Setter
    @Column(nullable = false)
    private String userPassword;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickName;

    @Setter
    @Column(length = 100)
    private String memo;

    @Setter
    private String name;

    protected UserAccount(){}

    public UserAccount(String userId, String userPassword, String email, String nickName, String name) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickName = nickName;
        this.name = name;
    }

    public static UserAccount of (String userId, String userPassword, String email, String nickName, String name){
        return new UserAccount(userId, userPassword, email, nickName, name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof UserAccount userAccount)) return false;
        return id != null && id.equals(userAccount.id);
    }

}