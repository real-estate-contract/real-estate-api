package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.AmenitiesDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AmenitiesResponse {

    Long id;
    String subway;
    String bus;
    String mart;
    String cafe;
    String laundry;
    String hospital;
    String bank;
    Long propertyId;

    public static AmenitiesResponse from(AmenitiesDto dto) {
        return new AmenitiesResponse(
            dto.getId(),
            dto.getSubway(),
            dto.getBus(),
            dto.getMart(),
            dto.getCafe(), dto.getLaundry(), dto.getHospital(),dto.getBank(),dto.getPropertyId());

    }
}
