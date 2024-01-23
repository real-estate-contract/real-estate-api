package com.realEstate.realEstate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenApiController {

//    private final OpenApiService openApiService;
    private final WebClient webClient;

    @Value("${api.building-key}")
    private String buildingkey;

    @Value("${api.land-key}")
    private String landkey;

    /**
     * 건축물대장 api
     * @param sigunguCd
     * @param bjdongCd
     * @return
     */
    @GetMapping("building-info/{sigunguCd}/{bjdongCd}")
    public String buildingInfoOpenApi(@PathVariable("sigunguCd") String sigunguCd,
                          @PathVariable("bjdongCd") String bjdongCd) throws IOException {

        StringBuilder returnAll = new StringBuilder();
        String url = "http://apis.data.go.kr/1613000/BldRgstService_v2/getBrBasisOulnInfo";

        String encodedSigunguCd = URLEncoder.encode(sigunguCd, StandardCharsets.UTF_8.toString());
        String encodedBjdongCd = URLEncoder.encode(bjdongCd, StandardCharsets.UTF_8.toString());
        String encodedServiceKey = URLEncoder.encode(buildingkey, StandardCharsets.UTF_8.toString());

        URI uri = URI.create(String.format("%s?sigunguCd=%s&bjdongCd=%s&platGbCd=0&ServiceKey=%s&_type=json",
                url, encodedSigunguCd, encodedBjdongCd, encodedServiceKey));

        log.info("building open api uri = [{}]", uri);

        String responseBody = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        returnAll.append(responseBody);
        return returnAll.toString();
    }

    /**
     * 토지대장 api
     * @param pnu
     * @return
     */
    @GetMapping("/land-info/{pnu}")
    public String landInfoOpenApi(@PathVariable String pnu) throws IOException {
        StringBuilder returnAll = new StringBuilder();
        String url = "http://api.vworld.kr/ned/data/ladfrlList";

        String encodedPnu = URLEncoder.encode(pnu, StandardCharsets.UTF_8.toString());
        String encodedServiceKey = URLEncoder.encode(landkey, StandardCharsets.UTF_8.toString());

        URI uri = URI.create(String.format("%s?pnu=%s&format=json&key=%s&domain=www.test.com/map.html",
                url, encodedPnu, encodedServiceKey));

        String responseBody = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        returnAll.append(responseBody);
        return returnAll.toString();
    }
}
