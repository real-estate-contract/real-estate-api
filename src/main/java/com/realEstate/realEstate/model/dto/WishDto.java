package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishDto {

    Long id;
    UserDto user;
    PropertyDto property;

    public static WishDto from(Wish entity) {
        return new WishDto(
                entity.getId(),
                UserDto.from(entity.getUser()),
                PropertyDto.from(entity.getProperty())
        );
    }
}
