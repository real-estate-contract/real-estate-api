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
    Structure structure;
    int price;
    private int weeklyFee;
    private boolean deposit;
    private int depositFee;
    boolean management;
    int managementFee;
    boolean UsageFee; // 개별 사용료
    boolean negotiationFee; // 가격 협의 가능
    boolean loanFund; // 융자
    LocalDate startDate;
    LocalDate endDate;
    int minimum;
    int roomCount;
    int bathroomCount;
    int area1;
    int area2;
    int floor;
    int wholeFloor;
    boolean parkingAvailable;
    boolean washingmachine;
    boolean airconditioner;
    boolean refrigerator;


    PropertyConditionDto propertyCondition;
    AddressDto address;
    UserDto user;
    List<String> imageUrls;


    public static PropertyDto from(Property entity) {
        return new PropertyDto(
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getPropertyId(),
                entity.getStructure(),
                entity.getPrice(),
                entity.getWeeklyFee(),
                entity.isDeposit(),
                entity.getDepositFee(),
                entity.isManagement(),
                entity.getManagementFee(),
                entity.isUsageFee(),
                entity.isNegotiationFee(),
                entity.isLoanFund(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getMinimum(),
                entity.getRoomCount(),
                entity.getBathroomCount(),
                entity.getArea1(),
                entity.getArea2(),
                entity.getFloor(),
                entity.getWholeFloor(),
                entity.isParkingAvailable(),
                entity.isWashingmachine(),
                entity.isAirconditioner(),
                entity.isRefrigerator(),
                PropertyConditionDto.from(entity.getPropertyCondition()),
                AddressDto.from(entity.getAddress()),
                UserDto.from(entity.getUser()),
                entity.getPropertyImageList().stream()
                        .map(PropertyImage::getImageUrl)
                        .collect(Collectors.toList())
        );
    }
}