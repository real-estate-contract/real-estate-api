package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.PropertyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropertyCreateResponse {

    Long propertyId;

    public static PropertyCreateResponse fromDto(PropertyDto dto) {
        return new PropertyCreateResponse(
                dto.getPropertyId()
        );
    }
}
