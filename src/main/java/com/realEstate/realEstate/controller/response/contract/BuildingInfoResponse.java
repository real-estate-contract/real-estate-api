package com.realEstate.realEstate.controller.response.contract;

import com.realEstate.realEstate.controller.response.UserJoinResponse;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.entity.BuildingInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class BuildingInfoResponse {
    private String mainPurpsCdNm; //주용도코드명
    private String etcRoof; //기타지붕
    private String useAprDay; //사용승인일
    private String newPlatPlc; //도로명대지위치
    private int archArea; //건축면적(㎡)
    private int bcRat; //건폐율(%)
    private int vlRat; //용적률(%)
    private String strctCdNm; //구조코드명

    //Entity -> Dto
    public BuildingInfoResponse(BuildingInfo buildingInfo) {
        this.mainPurpsCdNm = buildingInfo.getMainPurpsCdNm();
        this.etcRoof = buildingInfo.getEtcRoof();
        this.useAprDay = buildingInfo.getUseAprDay();
        this.newPlatPlc = buildingInfo.getNewPlatPlc();
        this.archArea = buildingInfo.getArchArea();
        this.bcRat = buildingInfo.getBcRat();
        this.vlRat = buildingInfo.getVlRat();
        this.strctCdNm = buildingInfo.getStrctCdNm();
    }
}