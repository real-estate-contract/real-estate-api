package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.PropertyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyCreateResponse {

    Long propertyId;

    public static PropertyCreateResponse fromDto(Long id) {
        return new PropertyCreateResponse(id);
    }
}
