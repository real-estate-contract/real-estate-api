package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Setter
@Table
public class BuildingInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingId;

    private String bjdongCd;
    private String bldNm;
    private String block;
    private String bun;
    private int bylotCnt;
    private String crtnDay;
    private String guyukCd;
    private String guyukCdNm;
    private String ji;
    private String jiguCd;
    private String jiguCdNm;
    private String jiyukCd;
    private String jiyukCdNm;
    private String lot;
    private String mgmBldrgstPk;
    private String mgmUpBldrgstPk;
    private String naBjdongCd;
    private double naMainBun;
    private String naRoadCd;
    private double naSubBun;
    private int naUgrndCd;
    private String newPlatPlc;
    private int platGbCd;
    private String platPlc;
    private int regstrGbCd;
    private String regstrGbCdNm;
    private int regstrKindCd;
    private String regstrKindCdNm;
    private int rnum;
    private String sigunguCd;
    private String splotNm;

    @Builder
    public BuildingInfo(String bjdongCd, String bldNm, String block, String bun, int bylotCnt,
                        String crtnDay, String guyukCd, String guyukCdNm, String ji, String jiguCd, String jiguCdNm,
                        String jiyukCd, String jiyukCdNm, String lot, String mgmBldrgstPk, String mgmUpBldrgstPk,
                        String naBjdongCd, double naMainBun, String naRoadCd, double naSubBun, int naUgrndCd,
                        String newPlatPlc, int platGbCd, String platPlc, int regstrGbCd, String regstrGbCdNm,
                        int regstrKindCd, String regstrKindCdNm, int rnum, String sigunguCd, String splotNm) {
        this.bjdongCd = bjdongCd;
        this.bldNm = bldNm;
        this.block = block;
        this.bun = bun;
        this.bylotCnt = bylotCnt;
        this.crtnDay = crtnDay;
        this.guyukCd = guyukCd;
        this.guyukCdNm = guyukCdNm;
        this.ji = ji;
        this.jiguCd = jiguCd;
        this.jiguCdNm = jiguCdNm;
        this.jiyukCd = jiyukCd;
        this.jiyukCdNm = jiyukCdNm;
        this.lot = lot;
        this.mgmBldrgstPk = mgmBldrgstPk;
        this.mgmUpBldrgstPk = mgmUpBldrgstPk;
        this.naBjdongCd = naBjdongCd;
        this.naMainBun = naMainBun;
        this.naRoadCd = naRoadCd;
        this.naSubBun = naSubBun;
        this.naUgrndCd = naUgrndCd;
        this.newPlatPlc = newPlatPlc;
        this.platGbCd = platGbCd;
        this.platPlc = platPlc;
        this.regstrGbCd = regstrGbCd;
        this.regstrGbCdNm = regstrGbCdNm;
        this.regstrKindCd = regstrKindCd;
        this.regstrKindCdNm = regstrKindCdNm;
        this.rnum = rnum;
        this.sigunguCd = sigunguCd;
        this.splotNm = splotNm;
    }
}
