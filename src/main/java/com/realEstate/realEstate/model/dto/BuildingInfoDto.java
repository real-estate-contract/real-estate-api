package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.BuildingInfo;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingInfoDto {

    private String mainPurpsCdNm; //주용도코드명
    private String etcRoof; //기타지붕
    private String useAprDay; //사용승인일
    private String newPlatPlc; //도로명대지위치
    private int archArea; //건축면적(㎡)
    private int bcRat; //건폐율(%)
    private int vlRat; //용적률(%)
    private String strctCdNm; //구조코드명

    //Dto -> Entity
    public BuildingInfo toEntity(){
        return BuildingInfo.builder()
                .mainPurpsCdNm(mainPurpsCdNm)
                .etcRoof(etcRoof)
                .useAprDay(useAprDay)
                .newPlatPlc(newPlatPlc)
                .archArea(archArea)
                .bcRat(bcRat)
                .vlRat(vlRat)
                .strctCdNm(strctCdNm)
                .build();
    }

}
