package com.realEstate.realEstate.model.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table
public class LandInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landId;

    private String ldCodeNm; //법정동명
    private String lndcgrCodeNm; //지목명
    private String lndpclAr; //면적(㎡)



}
