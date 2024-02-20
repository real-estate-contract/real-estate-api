package com.realEstate.realEstate.util.oauth.login.handler;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.util.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Value("${jwt.access.expiration}")
    private String accessTokenExpiration;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) {
//        String email = extractUsername(authentication); // 인증 정보에서 Username(email) 추출
//        User user = userRepository.findByName(authentication.getName()).orElseThrow(()->{
//            throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음");
//        });
//        String accessToken = jwtService.createAccessToken(email,user.getEmail(), secretKey, expiredTimeMs); // JwtService의 createAccessToken을 사용하여 AccessToken 발급
//        String refreshToken = jwtService.createRefreshToken(); // JwtService의 createRefreshToken을 사용하여 RefreshToken 발급
//
//        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken); // 응답 헤더에 AccessToken, RefreshToken 실어서 응답
//
//        userRepository.findByEmail(email)
//                .ifPresent(user -> {
//                    user.updateRefreshToken(refreshToken);
//                    userRepository.saveAndFlush(user);
//                });
//        log.info("로그인에 성공하였습니다. 이메일 : {}", email);
//        log.info("로그인에 성공하였습니다. AccessToken : {}", accessToken);
//        log.info("발급된 AccessToken 만료 기간 : {}", accessTokenExpiration);
//    }

    private String extractUsername(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}