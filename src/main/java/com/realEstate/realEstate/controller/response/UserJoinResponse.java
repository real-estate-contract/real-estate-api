package com.realEstate.realEstate.controller.response;

import com.realEstate.realEstate.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponse {

    private Integer id;
    private String name;

    public static UserJoinResponse fromDTO(UserDto dto) {
        return new UserJoinResponse(
                dto.getUserId(),
                dto.getUsername()
        );
    }
}
