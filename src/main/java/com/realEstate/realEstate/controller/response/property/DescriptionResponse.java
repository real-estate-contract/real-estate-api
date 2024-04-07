package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.DescriptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DescriptionResponse {

    Long id;
    String lineMemo;
    String memo;
    boolean loanAvailable;
    boolean petFriendly;
    Long propertyId;

    public static DescriptionResponse fromDto(DescriptionDto dto) {
        return new DescriptionResponse(
                dto.getId(),
                dto.getLineMemo(),
                dto.getMemo(),
                dto.isLoanAvailable(),
                dto.isPetFriendly(),
                dto.getPropertyId()
        );
    }
}
