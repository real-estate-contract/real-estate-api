package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.TermUnit;
import com.realEstate.realEstate.model.entity.Contract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class ContractDto {

    private Long contractId;
    private CType type;
    private BigDecimal contractAmount;
    private Date contractDate;
    private TermUnit termUnit;
    private int termLength;
    private String conditions;
    private PropertyDto property;
    private UserDto buyer;

    public ContractDto(Long contractId, CType type, BigDecimal contractAmount, Date contractDate,
                       TermUnit termUnit, int termLength, String conditions, PropertyDto property, UserDto buyer) {
        this.contractId = contractId;
        this.type = type;
        this.contractAmount = contractAmount;
        this.contractDate = contractDate;
        this.termUnit = termUnit;
        this.termLength = termLength;
        this.conditions = conditions;
        this.property = property;
        this.buyer = buyer;
    }

    public static ContractDto from(Contract entity) {
        return new ContractDto(
                entity.getContractId(),
                entity.getType(),
                entity.getContractAmount(),
                entity.getContractDate(),
                entity.getTermUnit(),
                entity.getTermLength(),
                entity.getConditions(),
                PropertyDto.from(entity.getProperty()),
                UserDto.from(entity.getBuyer())
        );
    }
}