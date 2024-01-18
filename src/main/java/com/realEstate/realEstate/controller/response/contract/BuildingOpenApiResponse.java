package com.realEstate.realEstate.controller.response.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class BuildingOpenApiResponse {

    private Response response;

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Response {
        private Buildingdata buildingdata;
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Buildingdata {
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
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Item {
        private String data;
    }

}