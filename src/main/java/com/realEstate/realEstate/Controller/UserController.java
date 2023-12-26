package com.realEstate.realEstate.Controller;

import com.realEstate.realEstate.Controller.request.userJoinRequest;
import com.realEstate.realEstate.Controller.request.userLoginRequest;
import com.realEstate.realEstate.Controller.response.Response;
import com.realEstate.realEstate.Controller.response.userJoinResponse;
import com.realEstate.realEstate.Controller.response.userLoginResponse;
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
    public Response<userJoinResponse> join(@RequestBody userJoinRequest request) {
        UserDto dto = userService.join(request.getName(), request.getPassword(), request.getEmail(), request.getGender(), request.getAge(), request.getRole());

        return Response.success(userJoinResponse.fromDTO(dto));
    }

    @PostMapping("/login")
    public Response<userLoginResponse> login(@RequestBody userLoginRequest request) {
        String token = userService.login(request.getName(), request.getPassword());
        log.info(token);
        return Response.success(new userLoginResponse(token));
    }
}
