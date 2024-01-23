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

    private String dong;
    private String bun;
    private String ji;
    private String sigunguCode;
    private String bjdongCode;

    private String pnu; //고유번호 (토지대장)

    private String mainPurpsCdNm; //주용도코드명
    private String etcRoof; //기타지붕
    private String useAprDay; //사용승인일
    private String newPlatPlc; //도로명대지위치
    private int archArea; //건축면적(㎡)
    private int bcRat; //건폐율(%)
    private int vlRat; //용적률(%)
    private String strctCdNm; //구조코드명



    public String setPnu() {
        pnu = sigunguCode + bjdongCode + dong + bun + ji;
        return pnu;
    }



    public static BuildingInfo of(String mainPurpsCdNm, String etcRoof, String useAprDay, String newPlatPlc,
                                  int archArea, int bcRat, int vlRat, String strctCdNm) {
        BuildingInfo buildingInfo = new BuildingInfo();
        buildingInfo.setMainPurpsCdNm(mainPurpsCdNm);
        buildingInfo.setEtcRoof(etcRoof);
        buildingInfo.setUseAprDay(useAprDay);
        buildingInfo.setNewPlatPlc(newPlatPlc);
        buildingInfo.setArchArea(archArea);
        buildingInfo.setBcRat(bcRat);
        buildingInfo.setVlRat(vlRat);
        buildingInfo.setStrctCdNm(strctCdNm);

        buildingInfo.setPnu();

        return buildingInfo;
    }


}
