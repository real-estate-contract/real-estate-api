package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.constant.Term;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Getter
@ToString
@Setter
@Table
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Integer id;

    @Column(name = "contract_type", nullable = false)
    private CType type;

    @Column(name = "contract_amount", nullable = false)
    private BigDecimal contractAmount;

    @Column(name = "contract_date", nullable = false)
    private Date contractDate;

    @Column(name = "term_unit", nullable = false)
    private Term termUnit;

    @Column(name = "term_length", nullable = false)
    private int termLength;

    @Column(name = "conditions")
    private String conditions;

    // TODO: Property와의 관계 매핑은 On3eToOne 또는 ManyToOne으로 변경해야 함

    @ManyToOne // Contract는 여러 개의 계약이 하나의 부동산과 연결될 수 있음
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne // 여러 개의 계약이 하나의 구매자와 연결될 수 있음
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    // 판매자 정보는 Property에서 가져올 수 있으므로 sellerID 필드는 제거

}
