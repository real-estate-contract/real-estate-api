package com.realEstate.realEstate.controller.request.contract;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Term;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@AllArgsConstructor
public class ContractCreateRequest {

    private Integer id;
    private CType type;
    private BigDecimal contractAmount;
    private Date contractDate;
    private Term termUnit;
    private int termLength;
    private String conditions;
    private PropertyDto property;
    private UserDto buyer;
}
