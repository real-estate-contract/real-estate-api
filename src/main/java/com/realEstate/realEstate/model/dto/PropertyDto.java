package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.PropertyImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    int managementFee;
    boolean condominium;
    int area;
    int floor;
    boolean parkingAvailable;
    boolean hasElevator;
    LocalDate moveInDate;
    Structure structure;
    String direction;
    PropertyConditionDto propertyCondition;
    AddressDto address;
    UserDto user;
    OptionDto option;
    AmenitiesDto amenities;
    DescriptionDto description;
    List<String> imageUrls;


    public static PropertyDto from(Property entity) {
        return new PropertyDto(
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getPropertyId(),
                entity.getTransactionType(),
                entity.getPrice(),
                entity.getDeposit(),
                entity.getMonthlyRent(),
                entity.getManagementFee(),
                entity.isCondominium(),
                entity.getArea(),
                entity.getFloor(),
                entity.isParkingAvailable(),
                entity.isHasElevator(),
                entity.getMoveInDate(),
                entity.getStructure(),
                entity.getDirection(),
                PropertyConditionDto.from(entity.getPropertyCondition()),
                AddressDto.from(entity.getAddress()),
                UserDto.from(entity.getUser()),
                OptionDto.from(entity.getOption()),
                AmenitiesDto.from(entity.getAmenities()),
                DescriptionDto.from(entity.getDescription()),
                entity.getPropertyImageList().stream()
                        .map(PropertyImage::getImageUrl)
                        .collect(Collectors.toList())
        );
    }
}