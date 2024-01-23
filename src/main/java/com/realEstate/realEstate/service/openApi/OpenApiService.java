//package com.realEstate.realEstate.service.openApi;
//
//import com.realEstate.realEstate.model.entity.BuildingInfo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class OpenApiService {
//
//    public List<BuildingInfo> init(String jsonData) {
//        List<BuildingInfo> buildingInfos = new ArrayList<>();
//
//        try {
//            System.out.println("JSON Data: " + jsonData); // 디버깅을 위해 JSON 데이터를 출력해 봅니다.
//
//            JSONObject jsonObj = new JSONObject(jsonData);
//            JSONObject parseResponse = jsonObj.getJSONObject("response");
//            JSONObject parseBody = parseResponse.getJSONObject("body");
//            JSONObject parseItems = parseBody.getJSONObject("items");
//            JSONArray parseItemList = parseItems.getJSONArray("item");
//
//            for (int i = 0; i < parseItemList.length(); i++) {
//                JSONObject jObj = parseItemList.getJSONObject(i);
//                BuildingInfo buildingInfo = jsonDataToBuildingInfo(jObj);
//                buildingInfos.add(buildingInfo);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return buildingInfos;
//    }
//
//
//
//    private BuildingInfo jsonDataToBuildingInfo(JSONObject jsonObject) {
//        return BuildingInfo.builder()
//                .bjdongCd(jsonObject.optString("bjdongCd", ""))
//                .bldNm(jsonObject.optString("bldNm", ""))
//                .block(jsonObject.optString("block", ""))
//                .bun(jsonObject.optString("bun", ""))
//                .bylotCnt(jsonObject.optInt("bylotCnt", 0))
//                .crtnDay(jsonObject.optString("crtnDay", ""))
//                .guyukCd(jsonObject.optString("guyukCd", ""))
//                .guyukCdNm(jsonObject.optString("guyukCdNm", ""))
//                .ji(jsonObject.optString("ji", ""))
//                .jiguCd(jsonObject.optString("jiguCd", ""))
//                .jiguCdNm(jsonObject.optString("jiguCdNm", ""))
//                .jiyukCd(jsonObject.optString("jiyukCd", ""))
//                .jiyukCdNm(jsonObject.optString("jiyukCdNm", ""))
//                .lot(jsonObject.optString("lot", ""))
//                .mgmBldrgstPk(jsonObject.optString("mgmBldrgstPk", ""))
//                .mgmUpBldrgstPk(jsonObject.optString("mgmUpBldrgstPk", ""))
//                .naBjdongCd(jsonObject.optString("naBjdongCd", ""))
//                .naMainBun(jsonObject.optDouble("naMainBun", 0.0))
//                .naRoadCd(jsonObject.optString("naRoadCd", ""))
//                .naSubBun(jsonObject.optDouble("naSubBun", 0.0))
//                .naUgrndCd(jsonObject.optInt("naUgrndCd", 0))
//                .newPlatPlc(jsonObject.optString("newPlatPlc", ""))
//                .platGbCd(jsonObject.optInt("platGbCd", 0))
//                .platPlc(jsonObject.optString("platPlc", ""))
//                .regstrGbCd(jsonObject.optInt("regstrGbCd", 0))
//                .regstrGbCdNm(jsonObject.optString("regstrGbCdNm", ""))
//                .regstrKindCd(jsonObject.optInt("regstrKindCd", 0))
//                .regstrKindCdNm(jsonObject.optString("regstrKindCdNm", ""))
//                .rnum(jsonObject.optInt("rnum", 0))
//                .sigunguCd(jsonObject.optString("sigunguCd", ""))
//                .splotNm(jsonObject.optString("splotNm", ""))
//                .build();
//    }
//}
