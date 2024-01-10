package com.realEstate.realEstate.controller.response.property;


import com.realEstate.realEstate.controller.response.UserResponse;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.PropertyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PropertyResponse {
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long propertyId;
    CType transactionType;
    int price;
    int deposit;
    int monthlyRent;
    int area;
    int floor;
    boolean parkingAvailable;
    boolean hasElevator;
    LocalDate moveInDate;
    Structure structure;
    AddressResponse address;
    UserResponse user;

    public static PropertyResponse fromDto(PropertyDto dto) {
        return new PropertyResponse(
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getPropertyId(),
                dto.getTransactionType(),
                dto.getPrice(),
                dto.getDeposit(),
                dto.getMonthlyRent(),
                dto.getArea(),
                dto.getFloor(),
                dto.isParkingAvailable(),
                dto.isHasElevator(),
                dto.getMoveInDate(),
                dto.getStructure(),
                AddressResponse.fromDto(dto.getAddress()),
                UserResponse.fromDto(dto.getUser())

        );
    }


}
