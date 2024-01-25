package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.controller.request.UserJoinRequest;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserJoinRequest request) throws Exception {
        UserDto dto = userService.join(request.getName(), request.getNickName(),request.getPassword(), request.getEmail(), request.getGender(), request.getAge(), request.getRole());

        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
