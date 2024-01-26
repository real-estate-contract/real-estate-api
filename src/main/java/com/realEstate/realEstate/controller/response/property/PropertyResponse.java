package com.realEstate.realEstate.controller.response.property;


import com.realEstate.realEstate.controller.response.UserResponse;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.dto.PropertyImageDto;
import com.realEstate.realEstate.model.entity.PropertyOption;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
   int managementFee;
    int area;
    int floor;
    boolean parkingAvailable;
    boolean hasElevator;
    LocalDate moveInDate;
    Structure structure;
    String direction;
    AddressResponse address;
    UserResponse user;
    OptionResponse option;
    AmenitiesResponse amenities;
    DescriptionResponse description;
    List<String> imageUrls;
    public static PropertyResponse fromDto(PropertyDto dto) {
        return new PropertyResponse(
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getPropertyId(),
                dto.getTransactionType(),
                dto.getPrice(),
                dto.getDeposit(),
                dto.getMonthlyRent(),
                dto.getManagementFee(),
                dto.getArea(),
                dto.getFloor(),
                dto.isParkingAvailable(),
                dto.isHasElevator(),
                dto.getMoveInDate(),
                dto.getStructure(),
                dto.getDirection(),
                AddressResponse.fromDto(dto.getAddress()),
                UserResponse.fromDto(dto.getUser()),
                OptionResponse.from(dto.getOption()),
                AmenitiesResponse.from(dto.getAmenities()),
                DescriptionResponse.fromDto(dto.getDescription()),
                dto.getImageUrls()

        );
    }


}
