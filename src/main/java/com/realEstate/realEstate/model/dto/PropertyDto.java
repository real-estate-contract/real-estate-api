package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Condominium;
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
    boolean management;
    int managementFee;
    Condominium condominium;
    int area;
    int wholeFloor;
    int floor;
    boolean parkingAvailable;
    boolean hasElevator;
    LocalDate moveInDate;
    Structure structure;
    String direction;
    boolean UsageFee; // 개별 사용료
    boolean negotiationFee; // 가격 협의 가능
    boolean loanFund; // 융자
    int year;
    int generationCount; // 전체 세대수


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
                entity.isManagement(),
                entity.getManagementFee(),
                entity.getCondominium(),
                entity.getArea(),
                entity.getWholeFloor(),
                entity.getFloor(),
                entity.isParkingAvailable(),
                entity.isHasElevator(),
                entity.getMoveInDate(),
                entity.getStructure(),
                entity.getDirection(),
                entity.isUsageFee(),
                entity.isNegotiationFee(),
                entity.isLoanFund(),
                entity.getYear(),
                entity.getGenerationCount(),
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