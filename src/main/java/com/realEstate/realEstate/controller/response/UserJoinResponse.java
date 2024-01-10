package com.realEstate.realEstate.controller.response;

import com.realEstate.realEstate.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class userJoinResponse {

    private Integer id;
    private String name;

    public static userJoinResponse fromDTO(UserDto dto) {
        return new userJoinResponse(
                dto.getId(),
                dto.getUsername()
        );
    }
}
