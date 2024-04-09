package com.realEstate.realEstate.controller.response.property;


import com.realEstate.realEstate.controller.response.UserResponse;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Condominium;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.dto.PropertyImageDto;
import com.realEstate.realEstate.model.dto.WishDto;
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
    Long propertyId; // 매물 id
    CType transactionType; // 매물유형(SALE//매매, JEONSE // 전세, MONTH_RENT // 월세)
    int price; //매매금
    int deposit; // 보증금
    int monthlyRent; //월세
    boolean management; // 관리비 있(True)?없(False)?
    int managementFee; // 관리비 가격
    Condominium condominium;
    // 공동주택 여부(APARTMENT_BUILDING : 공동주택,DETACHED_HOUSED : 단독주택,
    // TYPE1NEIGHBORHOODFACILITY : 1종 근린 시설,TYPE2NEIGHBORHOODFACILITY : 2종 근린 시설,)
    int area; //평형수
    int wholeFloor; //전체층
    int floor; // 해당층
    boolean parkingAvailable; // 개인주차 가능 여부(True/ False)
    boolean hasElevator; //엘레베이터
    LocalDate moveInDate; // 입주가능날짜
    Structure structure; //구조 APART("아파트"),VILLA("빌라/투룸+"),ONE_ROOM("원룸"),OFFICETEL("오피스텔");
    String direction; // 창문 방향
    boolean UsageFee; // 개별 사용료
    boolean negotiationFee; // 가격 협의 가능
    boolean loanFund; // 융자
    int year; // 준공년도
    int generationCount; // 전체 세대수
    PropertyConditionResponse propertyCondition;
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
                dto.isManagement(),
                dto.getManagementFee(),
                dto.getCondominium(),
                dto.getArea(),
                dto.getWholeFloor(),
                dto.getFloor(),
                dto.isParkingAvailable(),
                dto.isHasElevator(),
                dto.getMoveInDate(),
                dto.getStructure(),
                dto.getDirection(),
                dto.isUsageFee(),
                dto.isNegotiationFee(),
                dto.isLoanFund(),
                dto.getYear(),
                dto.getGenerationCount(),
                PropertyConditionResponse.fromDto(dto.getPropertyCondition()),
                AddressResponse.fromDto(dto.getAddress()),
                UserResponse.fromDto(dto.getUser()),
                OptionResponse.from(dto.getOption()),
                AmenitiesResponse.from(dto.getAmenities()),
                DescriptionResponse.fromDto(dto.getDescription()),
                dto.getImageUrls()

        );
    }

    public static PropertyResponse from(WishDto dto){
        return new PropertyResponse(
                dto.getProperty().getCreatedAt(),
                dto.getProperty().getUpdatedAt(),
                dto.getProperty().getPropertyId(),
                dto.getProperty().getTransactionType(),
                dto.getProperty().getPrice(),
                dto.getProperty().getDeposit(),
                dto.getProperty().getMonthlyRent(),
                dto.getProperty().isManagement(),
                dto.getProperty().getManagementFee(),
                dto.getProperty().getCondominium(),
                dto.getProperty().getArea(),
                dto.getProperty().getWholeFloor(),
                dto.getProperty().getFloor(),
                dto.getProperty().isParkingAvailable(),
                dto.getProperty().isHasElevator(),
                dto.getProperty().getMoveInDate(),
                dto.getProperty().getStructure(),
                dto.getProperty().getDirection(),
                dto.getProperty().isUsageFee(),
                dto.getProperty().isNegotiationFee(),
                dto.getProperty().isLoanFund(),
                dto.getProperty().getYear(),
                dto.getProperty().getGenerationCount(),
                PropertyConditionResponse.fromDto(dto.getProperty().getPropertyCondition()),
                AddressResponse.fromDto(dto.getProperty().getAddress()),
                UserResponse.fromDto(dto.getUser()),
                OptionResponse.from(dto.getProperty().getOption()),
                AmenitiesResponse.from(dto.getProperty().getAmenities()),
                DescriptionResponse.fromDto(dto.getProperty().getDescription()),
                dto.getProperty().getImageUrls()


        );
    }


}
