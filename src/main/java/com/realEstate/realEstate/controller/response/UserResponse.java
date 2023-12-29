package com.realEstate.realEstate.controller.response;


import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import com.realEstate.realEstate.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Integer userId;
    private String userName;
    private String email; // 명세서에서 추가된 부분
    private Gender gender;
    private int age;
    private UserRole role;

    public static UserResponse fromDto(UserDto dto) {
        return new UserResponse(
                dto.getUserId(),
                dto.getName(),
                dto.getEmail(),
                dto.getGender(),
                dto.getAge(),
                dto.getRole()
        );
    }


}
