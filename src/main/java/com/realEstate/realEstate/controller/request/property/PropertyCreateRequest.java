package com.realEstate.realEstate.controller.request.property;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Condominium;
import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.AddressDto;
import com.realEstate.realEstate.model.dto.PropertyConditionDto;
import com.realEstate.realEstate.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@AllArgsConstructor
public class PropertyCreateRequest {


    int price; //
    int weeklyFee; // 주차임
    boolean deposit; //보증금 여부
    int depositFee; //보증금
    Structure structure; //매물유형
    boolean management; //관리비 여부
    int managementFee; // 관리비
    boolean UsageFee; // 개별 사용료
    boolean negotiationFee; // 가격 협의 가능
    boolean loanFund; // 융자
    LocalDate startDate;
    LocalDate endDate;
    int minimum; //최소계약기간
    int roomCount; //방개수
    int bathroomCount; // 욕실
    int area1; // 공급면적
    int area2; //전용면적
    int floor; //해당층
    int wholeFloor; // 전체층
    boolean parkingAvailable; //주차가능여부
    boolean washingmachine; // 에어컨
    boolean airconditioner; //냉장고
    boolean refrigerator; // 세탁기





}
