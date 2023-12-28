package com.realEstate.realEstate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    //비밀번호 암호화를 위한 클래스
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder(); // 사용자 비빌번호를 안전하게 관리 하기 위한 BCrypt 알고리즘 사용
    }
}