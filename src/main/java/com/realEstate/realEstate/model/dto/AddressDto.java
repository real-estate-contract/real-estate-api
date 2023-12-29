package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.realEstate.realEstate.model.entity.Address}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressDto {
    Long addressId;
    String streetAddress;
    String city;
    boolean owner;

    public static AddressDto from(Address entity) {
        return new AddressDto(
                entity.getAddressId(),
                entity.getStreetAddress(),
                entity.getCity(),
                entity.isOwner()
        );
    }
}