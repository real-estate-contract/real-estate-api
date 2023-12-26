package com.realEstate.realEstate.Controller.request;

import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class userJoinRequest {

    private String name;
    private String password;
    private String email;
    private Gender gender;
    private int age;
    private UserRole role;



}
