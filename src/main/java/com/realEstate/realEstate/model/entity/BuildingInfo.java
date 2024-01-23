package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table
public class BuildingInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingId;

    private String mainPurpsCdNm; //주용도코드명
    private String etcRoof; //기타지붕
    private String useAprDay; //사용승인일
    private String newPlatPlc; //도로명대지위치
    private int archArea; //건축면적(㎡)
    private int bcRat; //건폐율(%)
    private int vlRat; //용적률(%)
    private String strctCdNm; //구조코드명

    private String bun;
    private String ji;
    private String sigunguCode;
    private String bjdongCode;

    @Builder.Default
    private String pnu = ""; //고유번호 (토지대장)

    public BuildingInfo() {
        // 기본 생성자에 초기화 로직 추가
        setPnu();
    }

    public void setPnu() {
        this.pnu = (sigunguCode != null ? sigunguCode : "") +
                (bjdongCode != null ? bjdongCode : "") +
                "1" +
                (bun != null ? bun : "") +
                (ji != null ? ji : "");
    }

    @Builder
    public BuildingInfo(String mainPurpsCdNm, String etcRoof, String useAprDay, String newPlatPlc,
                        int archArea, int bcRat, int vlRat, String strctCdNm) {
        this.mainPurpsCdNm = mainPurpsCdNm;
        this.etcRoof = etcRoof;
        this.useAprDay = useAprDay;
        this.newPlatPlc = newPlatPlc;
        this.archArea = archArea;
        this.bcRat = bcRat;
        this.vlRat = vlRat;
        this.strctCdNm = strctCdNm;
        setPnu();

    }
}
