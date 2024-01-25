package com.realEstate.realEstate.controller.request;

import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserJoinRequest {

    private String name;
    private String nickName;
    private String password;
    private String email;
    private Gender gender;
    private int age;
    private UserRole role;



}
