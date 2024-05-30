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





}
