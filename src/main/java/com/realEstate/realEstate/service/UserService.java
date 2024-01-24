package com.realEstate.realEstate.service;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.repository.cacheRepository.UserCacheRepository;
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
    private final UserCacheRepository redisRepository

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    @Transactional
    public UserDto join(String userName, String nickName, String password, String email, Gender gender, int age, UserRole userRole) {
        userRepository.findByName(userName).ifPresent(it -> {
            throw new ApplicationException(ErrorCode.Duplicated_USER_NAME, String.format("%s is duplicated", userName));
        });

        User user = userRepository.save(User.of(userName, nickName, encoder.encode(password), email, gender, age, userRole));
        return UserDto.from(user);

    }

    @Transactional
    public UserDto socialJoin(Long userId, String nickName, Gender gender, int age){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", userId));
        });

        user.setNickName(nickName);
        user.setGender(gender);
        user.setAge(age);
        user.setRole(UserRole.USER);

        userRepository.save(user);

        return UserDto.from(user);
    }

    @Transactional
    public String login(String userName, String password) {
        User user = redisRepository.getUser(userName).orElseGet(() ->userRepository.findByName(userName).orElseThrow(()->{
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName));
        }));

        if (!encoder.matches(password, user.getPassword())) {
            throw new ApplicationException(ErrorCode.INVALID_PASSWORD);
        }

        redisRepository.setUser(user);

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
