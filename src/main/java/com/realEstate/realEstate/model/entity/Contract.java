package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.TermUnit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;


@Entity
@Getter
@ToString
@Setter
@Table
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    @Enumerated(EnumType.STRING)
    private CType transactionType;

    private BigDecimal contractAmount;

    private Date contractDate;

    private TermUnit termUnit;

    private int termLength;

    private String conditions;


    // TODO : 주소 클래스로 빼기
    private String sigunguCd;
    private String bjdongCd;


    @ManyToOne // Contract는 여러 개의 계약이 하나의 부동산과 연결될 수 있음
    @JoinColumn(name = "propertyId")
    private Property property;

    @ManyToOne // 여러 개의 계약이 하나의 구매자와 연결될 수 있음
    @JoinColumn(name = "buyerId")
    private User buyer;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public static Contract of(CType transactionType, BigDecimal contractAmount, Date contractDate,
                              TermUnit termUnit, int termLength, String conditions, Property property, User buyer) {
        Contract contract = new Contract();
        contract.setTransactionType(transactionType);
        contract.setContractAmount(contractAmount);
        contract.setContractDate(contractDate);
        contract.setTermUnit(termUnit);
        contract.setTermLength(termLength);
        contract.setConditions(conditions);
        contract.setBuyer(buyer);
        contract.setProperty(property);

        return contract;
    }


    // 판매자 정보는 Property에서 가져올 수 있으므로 sellerID 필드는 제거

}