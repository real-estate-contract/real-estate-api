package com.realEstate.realEstate.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtTokenUtils {

    private String secretKey;

    //JSON Web Token(JWT)을 생성하고 검증하는 유틸리티 클래스

    public static Boolean validate(String token, UserDetails userDetails, String key) {
        String username = getUsername(token, key);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token, key);
    }


    //claims
    public static Claims extractAllClaims(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getUsername(String token, String key) {
        return extractAllClaims(token, key).get("username", String.class);
    }

    private static Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Boolean isTokenExpired(String token, String key) {
        Date expiration = extractAllClaims(token, key).getExpiration();
        return expiration.before(new Date());
    }

    public static String generateAccessToken(String username, String key, long expiredTimeMs) {
        return doGenerateToken(username, key, expiredTimeMs);
    }

    public static String doGenerateToken(String username,String key ,long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰에서 Email을 추출한다.
    public String getUid(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // verifyToken
    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey) // 비밀키를 설정하여 파싱한다.
                    .parseClaimsJws(token);  // 주어진 토큰을 파싱하여 Claims 객체를 얻는다.
            // 토큰의 만료 시간과 현재 시간비교
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());  // 만료 시간이 현재 시간 이후인지 확인하여 유효성 검사 결과를 반환
        } catch (Exception e) {
            log.info("토큰 만료 = {}", token);
            return false;
        }
    }



}