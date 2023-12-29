package com.realEstate.realEstate.controller.response.Property;

import com.realEstate.realEstate.model.dto.DescriptionDto;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.entity.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DescriptionResponse {

    Long id;
    String memo;
    boolean loanAvailable;
    boolean petFriendly;
    PropertyResponse property;

    public static DescriptionResponse fromDto(DescriptionDto dto) {
        return new DescriptionResponse(
                dto.getId(),
                dto.getMemo(),
                dto.isLoanAvailable(),
                dto.isPetFriendly(),
                PropertyResponse.fromDto(dto.getProperty())
        );
    }
}
