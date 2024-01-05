package com.realEstate.realEstate.controller.response.contract;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.TermUnit;
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
    private Long contractId;
    private CType type;
    private BigDecimal contractAmount;
    private Date contractDate;
    private TermUnit termUnit;
    private int termLength;
    private String conditions;
    private PropertyDto property;
    private UserDto buyer;

    public static ContractResponse fromDto(ContractDto dto){
        return new ContractResponse(
                dto.getContractId(),
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