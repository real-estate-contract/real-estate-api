package com.realEstate.realEstate.controller.response.contract;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Term;
import com.realEstate.realEstate.model.dto.ContractDto;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@AllArgsConstructor
public class ContractResponse {
    private Integer id;
    private CType type;
    private BigDecimal contractAmount;
    private Date contractDate;
    private Term termUnit;
    private int termLength;
    private String conditions;
    private PropertyDto property;
    private UserDto buyer;

    public static ContractResponse fromDto(ContractDto dto){
        return new ContractResponse(
                dto.getId(),
                dto.getType(),
                dto.getContractAmount(),
                dto.getContractDate(),
                dto.getTermUnit(),
                dto.getTermLength(),
                dto.getConditions(),
                dto.getProperty(),
                dto.getBuyer()
        );
    }

}