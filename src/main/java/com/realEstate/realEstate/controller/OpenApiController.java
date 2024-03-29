package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.controller.response.contract.BuildingInfoResponse;
import com.realEstate.realEstate.controller.response.contract.LandInfoResponse;
import com.realEstate.realEstate.controller.response.contract.LandPriceResponse;
import com.realEstate.realEstate.controller.response.contract.LandRightResponse;
import com.realEstate.realEstate.service.contract.BuildingInfoService;
import com.realEstate.realEstate.service.contract.LandInfoService;
import com.realEstate.realEstate.service.contract.LandPriceInfoService;
import com.realEstate.realEstate.service.contract.LandRightInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OpenApiController {

    private final BuildingInfoService buildingInfoService;
    private final LandInfoService landInfoService;
    private final LandRightInfoService landRightInfoService;
    private final LandPriceInfoService landPriceInfoService;

    /**
     * 건축물대장 api
     * @param sigunguCd
     * @param bjdongCd
     * @return
     */
    @GetMapping("building-info/{sigunguCd}/{bjdongCd}")
    public ResponseEntity<List<BuildingInfoResponse>> buildingInfoOpenApi(
            @PathVariable String sigunguCd, @PathVariable String bjdongCd) throws IOException {

        List<BuildingInfoResponse> buildingInfoList = buildingInfoService.getBuildingInfo(sigunguCd, bjdongCd);
        return ResponseEntity.ok().body(buildingInfoList);
    }

    /**
     * 토지대장 api
     * @param pnu
     * @return
     */
    @GetMapping("/land-info/{pnu}")
    public ResponseEntity<List<LandInfoResponse>> landInfoOpenApi(@PathVariable String pnu) throws IOException {

        List<LandInfoResponse> landInfoList = landInfoService.getLandInfo(pnu);
        return ResponseEntity.ok().body(landInfoList);
    }

    /**
     * 대지권등록목록조회 api
     * @param pnu
     * @return
     */
    @GetMapping("/land-right-info/{pnu}")
    public ResponseEntity<List<LandRightResponse>> landRightInfoOpenApi(@PathVariable String pnu) throws IOException {

        List<LandRightResponse> landInfoList = landRightInfoService.getLandInfo(pnu);
        return ResponseEntity.ok().body(landInfoList);
    }

    @GetMapping("/land-price-info/{pnu}")
    public ResponseEntity<List<LandPriceResponse>> landPriceInfoOpenApi(@PathVariable String pnu) throws IOException {

        List<LandPriceResponse> landInfoList = landPriceInfoService.getLandInfo(pnu);
        return ResponseEntity.ok().body(landInfoList);
    }
}
