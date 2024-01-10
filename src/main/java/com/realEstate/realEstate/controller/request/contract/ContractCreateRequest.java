package com.realEstate.realEstate.controller.request.contract;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.TermUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@AllArgsConstructor
public class ContractCreateRequest {

    CType transactionType;
    private BigDecimal contractAmount;
    private Date contractDate;
    TermUnit termUnit;
    private int termLength;
    private String conditions;
}