package com.realEstate.realEstate.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LandInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landId;

    private String ldCodeNm; //법정동명
    private String lndcgrCodeNm; //지목명
    private String lndpclAr; //면적(㎡)

    @Builder
    public LandInfo(String ldCodeNm, String lndcgrCodeNm, String lndpclAr) {
        this.ldCodeNm = ldCodeNm;
        this.lndcgrCodeNm = lndcgrCodeNm;
        this.lndpclAr = lndpclAr;
    }

}
