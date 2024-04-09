package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.DescriptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DescriptionResponse {

    Long id; // 매물 설명id
    String lineMemo; // 한줄 설명
    String memo; // 상세 설명
    boolean loanAvailable; // 대출 가능 여부
    boolean petFriendly; // 애완동물 가능여부
    Long propertyId; // propertyId

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
