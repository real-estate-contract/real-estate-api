package com.realEstate.realEstate.controller.request;

import com.realEstate.realEstate.model.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequest {

    private String nickName;
    private String password;
    private String email;
    private Gender gender;
    private int age;
}
