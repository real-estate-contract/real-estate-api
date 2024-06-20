package com.realEstate.realEstate.controller.response.contract;

import com.realEstate.realEstate.controller.response.UserResponse;
import com.realEstate.realEstate.controller.response.property.PropertyResponse;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ContractResponse {
    // 계약 아이디
    private final Long id;

    // 임대할 부분
    private final String section;

    // 주택 유형
    private final String propertyType;

    // 사용 기간
    private final String contractDateFrom;

    private final String contractDateTo;

    // 계약금
    private final BigDecimal contractPrice;

    // 계약금 지불일
    private final Date contractPriceDate;

    // 중도금
    private final BigDecimal Installment;

    // 중도금 지불일
    private final Date InstallmentDate;

    // 잔금
    private final BigDecimal balance;

    // 잔금 지불일
    private final Date balanceDate;

    // 주차임 지불일
    private final Date parkFeeDate;

    // 관리비 지불일
    private final Date loanAmountDate;

    // 공과금
    private final boolean utilities;

    // 인터넷
    private final boolean internet;

    // 사용자
    private final UserResponse user;

    // 매물
    private final PropertyResponse property;


    public static ContractResponse of(Contract contract){

        return new ContractResponse(
                contract.getId(),
                contract.getSection(),
                contract.getPropertyType(),
                contract.getContractDateFrom(),
                contract.getContractDateTo(),
                contract.getContractPrice(),
                contract.getContractPriceDate(),
                contract.getInstallment(),
                contract.getInstallmentDate(),
                contract.getBalance(),
                contract.getBalanceDate(),
                contract.getParkFeeDate(),
                contract.getLoanAmountDate(),
                contract.isUtilities(),
                contract.isInternet(),
                UserResponse.fromDto(UserDto.from(contract.getUser())),
                PropertyResponse.fromDto(PropertyDto.from(contract.getProperty()))
        );
    }
}
