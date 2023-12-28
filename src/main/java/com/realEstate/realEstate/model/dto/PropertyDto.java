package com.realEstate.realEstate.model.dto;


import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PropertyDto {
    private Integer id;
    private HType type;
    private BigDecimal price;
    private String address;
    private int area;
    private UserDto user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static PropertyDto from(Property entity) {
        return new PropertyDto(
                entity.getId(),
                entity.getType(),
                entity.getPrice(),
                entity.getAddress(),
                entity.getArea(),
                UserDto.from(entity.getUser()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }


}
