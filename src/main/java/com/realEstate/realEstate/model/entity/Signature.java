package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.entity.Contract;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@ToString
@Setter
@Table(name = "signature")
public class Signature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "signature_id")
    private Long id;

    @Lob // 대용량 데이터를 저장하기 위한 어노테이션
    @Column(name = "signature_data", nullable = false)
    private Blob signatureData;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

}
