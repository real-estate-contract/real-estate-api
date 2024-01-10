package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.realEstate.realEstate.model.entity.Property}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PropertyDto implements Serializable {
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
    AddressDto address;
    UserDto user;
    OptionDto option;
    DescriptionDto description;

    public static PropertyDto from(Property entity) {
        return new PropertyDto(
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getPropertyId(),
                entity.getTransactionType(),
                entity.getPrice(),
                entity.getDeposit(),
                entity.getMonthlyRent(),
                entity.getArea(),
                entity.getFloor(),
                entity.isParkingAvailable(),
                entity.isHasElevator(),
                entity.getMoveInDate(),
                entity.getStructure(),
                AddressDto.from(entity.getAddress()),
                UserDto.from(entity.getUser()),
                OptionDto.from(entity.getOption()),
                DescriptionDto.from(entity.getDescription())
        );
    }
}