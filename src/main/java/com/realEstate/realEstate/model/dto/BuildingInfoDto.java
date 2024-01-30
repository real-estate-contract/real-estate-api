package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.BuildingInfo;
import com.realEstate.realEstate.model.entity.LandInfo;
import lombok.*;

@Getter
@Builder
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

    @Builder
    public BuildingInfoDto(String mainPurpsCdNm, String etcRoof, String useAprDay,
                           String newPlatPlc, int archArea, int bcRat, int vlRat, String strctCdNm) {
        this.mainPurpsCdNm = mainPurpsCdNm;
        this.etcRoof = etcRoof;
        this.useAprDay = useAprDay;
        this.newPlatPlc = newPlatPlc;
        this.archArea = archArea;
        this.bcRat = bcRat;
        this.vlRat = vlRat;
        this.strctCdNm = strctCdNm;
    }

    public static BuildingInfoDto fromEntity(BuildingInfoDto entity) {
        return BuildingInfoDto.builder()
                .mainPurpsCdNm(entity.getMainPurpsCdNm())
                .etcRoof(entity.getEtcRoof())
                .useAprDay(entity.getUseAprDay())
                .newPlatPlc(entity.getNewPlatPlc())
                .archArea(entity.getArchArea())
                .bcRat(entity.getBcRat())
                .vlRat(entity.getVlRat())
                .strctCdNm(entity.getStrctCdNm())
                .build();
    }

}
