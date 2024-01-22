package com.realEstate.realEstate.service;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    @Transactional
    public UserDto join(String userName, String password, String email, Gender gender, int age, UserRole userRole) {
        // 회원가입하려는 userName이 이미 있다면 예외
        userRepository.findByName(userName).ifPresent(it -> {
            throw new ApplicationException(ErrorCode.Duplicated_USER_NAME, String.format("%s is duplicated", userName));
        });

        // 회원가입
        User user = userRepository.save(User.of(userName, encoder.encode(password), email, gender, age, userRole));
        return UserDto.from(user);

    }

    @Transactional
    // 토큰을 사용할 거기 때문에 String으로 받아줌
    public String login(String userName, String password) {
        // 회원가입 여부 체크
        User user = userRepository.findByName(userName).orElseThrow(()->{
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName));
        });

        //비밀번호 체크
        if (!encoder.matches(password, user.getPassword())) {
            throw new ApplicationException(ErrorCode.INVALID_PASSWORD);
        }

        //토큰 생성
        String token = JwtTokenUtils.doGenerateToken(userName, secretKey,expiredTimeMs);



        return token;
    }

    public UserDto loadUserByUsername(String userName) {
        User user = userRepository.findByName(userName).orElseThrow(()->{
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName));
        });

        return UserDto.from(user);

    }
}
