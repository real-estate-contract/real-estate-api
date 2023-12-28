package com.realEstate.realEstate.Controller.response.Property;


import com.realEstate.realEstate.Controller.response.UserResponse;
import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.dto.PropertyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PropertyResponse {
    private Integer id;
    private HType type;
    private BigDecimal price;
    private String address;
    private int area;
    private UserResponse user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PropertyResponse fromDto(PropertyDto dto) {
        return new PropertyResponse(
                dto.getId(),
                dto.getType(),
                dto.getPrice(),
                dto.getAddress(),
                dto.getArea(),
                UserResponse.fromDto(dto.getUser()),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
    }


}
