package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AddressResponse {

    Long addressId;
    String streetAddress;
    String city;
    boolean owner;

    public static AddressResponse fromDto(AddressDto dto) {
        return new AddressResponse(
                dto.getAddressId(),
                dto.getStreetAddress(),
                dto.getCity(),
                dto.isOwner()
        );
    }
}
