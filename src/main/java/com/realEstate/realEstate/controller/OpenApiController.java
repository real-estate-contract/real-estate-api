package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.controller.response.contract.BuildingInfoResponse;
import com.realEstate.realEstate.service.contract.BuildingInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class OpenApiController {

    private final BuildingInfoService buildingInfoService;

    @Autowired
    public OpenApiController(BuildingInfoService buildingInfoService){
        this.buildingInfoService = buildingInfoService;
    }

    @Value("${api.land-key}")
    private String landkey;

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
//    @GetMapping("/land-info/{pnu}")
//    public String landInfoOpenApi(@PathVariable String pnu) throws IOException {
//        StringBuilder returnAll = new StringBuilder();
//        String url = "http://api.vworld.kr/ned/data/ladfrlList";
//
//        String encodedPnu = URLEncoder.encode(pnu, StandardCharsets.UTF_8.toString());
//        String encodedServiceKey = URLEncoder.encode(landkey, StandardCharsets.UTF_8.toString());
//
//        URI uri = URI.create(String.format("%s?pnu=%s&format=json&key=%s&domain=www.test.com/map.html",
//                url, encodedPnu, encodedServiceKey));
//
//        String responseBody = webClient.get()
//                .uri(uri)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        returnAll.append(responseBody);
//        return returnAll.toString();
//    }
}
