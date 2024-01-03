package com.realEstate.realEstate.config.filter;

import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.service.UserService;
import com.realEstate.realEstate.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        // header가 null이거나 "Bearer "로 시작하지 않는 경우 처리
        if (header == null || !header.startsWith("Bearer ")) {
            log.warn("Authorization Header does not start with Bearer");
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        UserDto userDetails = userService.loadUserByUsername(JwtTokenUtils.getUsername(token, secretKey));

        if (!JwtTokenUtils.validate(token, userDetails, secretKey)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}
//import com.realEstate.realEstate.model.dto.UserDto;
//import com.realEstate.realEstate.service.UserService;
//import com.realEstate.realEstate.util.JwtTokenUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    private final UserService userService;
//
//    private final String secretKey;
//
//    private final static List<String> TOKEN_IN_PARAM_URLS = List.of("/realEstate/user");
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain)
//            throws ServletException, IOException {
//
//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String token;
//        try {
//            if (TOKEN_IN_PARAM_URLS.contains(request.getRequestURI())) {
//                log.info("Request with {} check the query param", request.getRequestURI());
//                token = request.getQueryString().split("=")[1].trim();
//            } else if (header == null || !header.startsWith("Bearer ")) {
//                log.error("Authorization Header does not start with Bearer {}", request.getRequestURI());
//                chain.doFilter(request, response);
//                return;
//            } else {
//                token = header.split(" ")[1].trim();
//            }
//
//            String userName = JwtTokenUtils.getUsername(token, secretKey);
//            UserDto userDetails = userService.loadUserByUsername(userName);
//
//            if (!JwtTokenUtils.validate(token, userDetails, secretKey)) {
//                chain.doFilter(request, response);
//                return;
//            }
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                    userDetails, null,
//                    userDetails.getAuthorities()
//            );
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (RuntimeException e) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        chain.doFilter(request, response);
//
//    }
//}