package com.koreait.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;


@EnableJpaAuditing
@Configuration
public class JpaConfig {
    @Bean// 데이터를 넣어주기 위해 사영 세팅도지 않은
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("관리자");    // 스프링 시큐리티로 안증 기능을 붙이게 될 때 수정할거임

    }

}
