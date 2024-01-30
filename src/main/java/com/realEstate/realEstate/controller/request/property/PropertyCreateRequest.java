package com.realEstate.realEstate.controller.request.property;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.AddressDto;
import com.realEstate.realEstate.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class PropertyCreateRequest {


    CType transactionType;
    int price;
    int deposit;
    int monthlyRent;
<<<<<<< HEAD
=======
    int managementFee;
    boolean condominium;
>>>>>>> b156b5d ([add] 매물 입지조건 추가)
    int area;
    int floor;
    boolean parkingAvailable;
    boolean hasElevator;
    LocalDate moveInDate;
    Structure structure;
//    Integer userId; // TODO: 없애주고 Authentication으로



}
