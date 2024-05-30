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
    Structure structure;
    String contractType = "단기";
    boolean paymentType;
    boolean management;
    int managementFee;
    boolean UsageFee; // 개별 사용료
    boolean negotiationFee; // 가격 협의 가능
    boolean loanFund; // 융자
    LocalDate startDate;
    LocalDate endDate;
    int roomCount;
    int bathroomCount;
    int area1;
    int area2;
    int floor;
    int wholeFloor;
    boolean parkingAvailable;
    PropertyConditionResponse propertyCondition;
    AddressResponse address;
    UserResponse user;
    List<String> imageUrls;
    public static PropertyResponse fromDto(PropertyDto dto) {
        return new PropertyResponse(
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getPropertyId(),
                dto.getStructure(),
                dto.getContractType(),
                dto.isPaymentType(),
                dto.isManagement(),
                dto.getManagementFee(),
                dto.isUsageFee(),
                dto.isNegotiationFee(),
                dto.isLoanFund(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getRoomCount(),
                dto.getBathroomCount(),
                dto.getArea1(),
                dto.getArea2(),
                dto.getFloor(),
                dto.getWholeFloor(),
                dto.isParkingAvailable(),
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
                dto.getProperty().getStructure(),
                dto.getProperty().getContractType(),
                dto.getProperty().isPaymentType(),
                dto.getProperty().isManagement(),
                dto.getProperty().getManagementFee(),
                dto.getProperty().isUsageFee(),
                dto.getProperty().isNegotiationFee(),
                dto.getProperty().isLoanFund(),
                dto.getProperty().getStartDate(),
                dto.getProperty().getEndDate(),
                dto.getProperty().getRoomCount(),
                dto.getProperty().getBathroomCount(),
                dto.getProperty().getArea1(),
                dto.getProperty().getArea2(),
                dto.getProperty().getFloor(),
                dto.getProperty().getWholeFloor(),
                dto.getProperty().isParkingAvailable(),
                PropertyConditionResponse.fromDto(dto.getProperty().getPropertyCondition()),
                AddressResponse.fromDto(dto.getProperty().getAddress()),
                UserResponse.fromDto(dto.getUser()),
                dto.getProperty().getImageUrls()


        );
    }


}
