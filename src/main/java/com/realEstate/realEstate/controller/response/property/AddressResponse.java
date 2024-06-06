package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AddressResponse {

    Long addressId;
    String streetAddress; // 도로명 주소

    public static AddressResponse fromDto(AddressDto dto) {
        return new AddressResponse(
                dto.getAddressId(),
                dto.getStreetAddress()
        );
    }
}
