package com.realEstate.realEstate.controller.request.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ContractRequest {
    // 임대할 부분
    private String section;

    // 주택 유형
    private String propertyType;

    // 사용 기간
    private String contractDateFrom;

    private String contractDateTo;

    // 계약금
    private BigDecimal contractPrice;

    // 계약금 지불일
    private Date contractPriceDate;

    // 중도금
    private BigDecimal Installment;

    // 중도금 지불일
    private Date InstallmentDate;

    // 잔금
    private BigDecimal balance;

    // 잔금 지불일
    private Date balanceDate;

    // 주차임 지불일
    private Date parkFeeDate;

    // 관리비 지불일
    private Date loanAmountDate;

    // 공과금
    private boolean utilities;

    // 인터넷
    private boolean internet;


}
