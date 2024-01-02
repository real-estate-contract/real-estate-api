package com.realEstate.realEstate.config;
////
////
////import com.realEstate.exception.CustomAuthenticationEntryPoint;
////import com.realEstate.realEstate.config.filter.JwtTokenFilter;
////import com.realEstate.realEstate.service.UserService;
////import lombok.RequiredArgsConstructor;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.builders.WebSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////@EnableWebSecurity
////@RequiredArgsConstructor
////public class AuthenticationConfig extends WebSecurityConfigurerAdapter {
////
////    private final UserService userService;
////
////    @Value("${jwt.secret-key}")
////    private String secretKey;
////
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().regexMatchers("^(?!/api/).*");
////        //여기서 필터링을 한 번 해줌
////        //regex = 정규화(정해진 문자 패턴을 정해줌)
////
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/realEstate/users/join", "/realEstate/users/login").permitAll()
////                .antMatchers("/realEstate/**").authenticated()
////                .anyRequest().permitAll()
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .exceptionHandling()
////                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
////                .and()
////                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class);
////    }
////}


import com.realEstate.exception.CustomAuthenticationEntryPoint;
import com.realEstate.realEstate.config.filter.JwtTokenFilter;
import com.realEstate.realEstate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg",
                        "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js", "/manifest.json", "/static/**")
                .antMatchers("/resources/**")
                .antMatchers(HttpMethod.POST, "/realEstate/users/join", "/realEstate/users/login")
                .antMatchers(HttpMethod.GET, "/post", "/authentication/sign-in", "/authentication/sign-up",
                        "/my-post", "/feed");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class);
    }

}