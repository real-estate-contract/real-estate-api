package com.realEstate.realEstate.controller.response.property;


import com.realEstate.realEstate.controller.response.UserResponse;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Condominium;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.*;
import com.realEstate.realEstate.model.entity.PropertyOption;
import com.realEstate.realEstate.model.entity.Wish;
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
    int price;
    Structure structure; // 매물유형
    int weeklyFee; // 주차금
    boolean deposit; // 보증금여부
    int depositFee; // 보증금
    boolean management; // 관리비여부
    int managementFee; // 관리비
    boolean UsageFee; // 개별 사용료
    boolean negotiationFee; // 가격 협의 가능
    boolean loanFund; // 융자
    LocalDate startDate; // 계약시작날짜
    LocalDate endDate; // 계약종료날짜
    int minimum; // 최소
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
    int propertyState;

    PropertyConditionResponse propertyCondition;
    AddressResponse address;
    UserResponse user;
    List<String> imageUrls;
    public static PropertyResponse fromDto(PropertyDto dto) {
        return new PropertyResponse(
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getPropertyId(),
                dto.getPrice(),
                dto.getStructure(),
                dto.getWeeklyFee(),
                dto.isDeposit(),
                dto.getDepositFee(),
                dto.isManagement(),
                dto.getManagementFee(),
                dto.isUsageFee(),
                dto.isNegotiationFee(),
                dto.isLoanFund(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getMinimum(),
                dto.getRoomCount(),
                dto.getBathroomCount(),
                dto.getArea1(),
                dto.getArea2(),
                dto.getFloor(),
                dto.getWholeFloor(),
                dto.isParkingAvailable(),
                dto.isWashingmachine(),
                dto.isAirconditioner(),
                dto.isRefrigerator(),
                dto.getPropertyState(),
                PropertyConditionResponse.fromDto(dto.getPropertyCondition()),
                AddressResponse.fromDto(dto.getAddress()),
                UserResponse.fromDto(dto.getUser()),
                dto.getImageUrls()

        );
    }

    public static PropertyResponse from(WishDto dto){
        return new PropertyResponse(
                dto.getProperty().getCreatedAt(),
                dto.getProperty().getUpdatedAt(),
                dto.getProperty().getPropertyId(),
                dto.getProperty().getPrice(),
                dto.getProperty().getStructure(),
                dto.getProperty().getWeeklyFee(),
                dto.getProperty().isDeposit(),
                dto.getProperty().getDepositFee(),
                dto.getProperty().isManagement(),
                dto.getProperty().getManagementFee(),
                dto.getProperty().isUsageFee(),
                dto.getProperty().isNegotiationFee(),
                dto.getProperty().isLoanFund(),
                dto.getProperty().getStartDate(),
                dto.getProperty().getEndDate(),
                dto.getProperty().getMinimum(),
                dto.getProperty().getRoomCount(),
                dto.getProperty().getBathroomCount(),
                dto.getProperty().getArea1(),
                dto.getProperty().getArea2(),
                dto.getProperty().getFloor(),
                dto.getProperty().getWholeFloor(),
                dto.getProperty().isParkingAvailable(),
                dto.getProperty().isWashingmachine(),
                dto.getProperty().isAirconditioner(),
                dto.getProperty().isRefrigerator(),
                dto.getProperty().getPropertyState(),
                PropertyConditionResponse.fromDto(dto.getProperty().getPropertyCondition()),
                AddressResponse.fromDto(dto.getProperty().getAddress()),
                UserResponse.fromDto(dto.getUser()),
                dto.getProperty().getImageUrls()


        );
    }


}
