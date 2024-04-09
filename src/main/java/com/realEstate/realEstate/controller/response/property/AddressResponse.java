package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AddressResponse {

    Long addressId;
    String streetAddress; // 도로명 주소
    String city; // 상세 주소
    boolean owner; // 자가 여부

    public static AddressResponse fromDto(AddressDto dto) {
        return new AddressResponse(
                dto.getAddressId(),
                dto.getStreetAddress(),
                dto.getCity(),
                dto.isOwner()
        );
    }
}
