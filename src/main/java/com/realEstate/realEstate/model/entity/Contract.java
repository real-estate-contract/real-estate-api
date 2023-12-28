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


@Entity
@Getter
@ToString
@Setter
@Table
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private CType type;

    @Column(nullable = false)
    private BigDecimal contractPrice;

    @Column(nullable = false)
    private Term termUnit;

    @Column(nullable = false)
    private int termLength;

    @Column
    private String conditions; //계약 조건

    //TODO : 밑에 세개는 다시 고민을 좀 해봐야할 것 같음

//    @OneToOne
//    private Property property;
//
//    @Column(nullable = false)
//    private Integer buyerID; //매물 구매자 id
//
//    @Column(nullable = false)
//    private Integer sellerID; // 이건 property안에 있으니깐 굳이 안해줘도 될 거 같은 느낌

}
