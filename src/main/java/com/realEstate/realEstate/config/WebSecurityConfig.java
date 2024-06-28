package com.realEstate.realEstate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("http://localhost:3000","http://3.34.191.254", "http://budong.com.s3-website.ap-northeast-2.amazonaws.com/",
                        "http://budong.com", "https://budong.com")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
