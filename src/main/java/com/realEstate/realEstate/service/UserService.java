package com.realEstate.realEstate.service;

import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


//    @Transactional
//    public UserDto join(String userName, String password, String email, Gender gender, int age, UserRole userRole) {
//        //회원가입 하려는 userName으로 회원가입된 user가 있는지
//
//    }
}
