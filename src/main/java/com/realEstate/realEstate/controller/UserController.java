package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.controller.request.UserJoinRequest;
import com.realEstate.realEstate.controller.request.UserLoginRequest;
import com.realEstate.realEstate.controller.response.Response;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/realEstate/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        UserDto dto = userService.join(request.getName(), request.getPassword(), request.getEmail(), request.getGender(), request.getAge(), request.getRole());

        return Response.success(UserJoinResponse.fromDTO(dto));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getName(), request.getPassword());
        log.info(token);
        return Response.success(new UserLoginResponse(token));
    }
}
