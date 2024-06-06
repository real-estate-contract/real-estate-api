package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@ToString
@Setter
@Table
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 임대할 부분
    private String section;

    // 주택 유형
    //TODO 외부 API

    // 사용 기간
    private String contractDateFrom;

    private String contractDateTo;

    // 보증금
    private BigDecimal loanAmount;

    // 주차임
    private BigDecimal parkFee;

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

    // 공과금
    private boolean utilities;

    // 인터넷
    private boolean internet;


    @ManyToOne // Contract는 여러 개의 계약이 하나의 부동산과 연결될 수 있음
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne // 여러 개의 계약이 하나의 구매자와 연결될 수 있음
    private User user;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}