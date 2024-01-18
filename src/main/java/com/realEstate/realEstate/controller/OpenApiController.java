package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.service.openApi.OpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiService openApiService;
    private final WebClient webClient;

    @Value("${api.building-key}")
    private String serviceKey;

    @GetMapping("/api/{sigunguCd}/{bjdongCd}")
    public String callApi(@PathVariable("sigunguCd") String sigunguCd,
                          @PathVariable("bjdongCd") String bjdongCd) throws IOException {

        StringBuilder returnAll = new StringBuilder();
        String url = "http://apis.data.go.kr/1613000/BldRgstService_v2/getBrBasisOulnInfo";

        // 각 파라미터를 URLEncoder를 사용하여 인코딩
        String encodedSigunguCd = URLEncoder.encode(sigunguCd, StandardCharsets.UTF_8.toString());
        String encodedBjdongCd = URLEncoder.encode(bjdongCd, StandardCharsets.UTF_8.toString());
        String encodedServiceKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8.toString());

        // URI 생성 (객체로 생성해줘야함!!!!)
        URI uri = URI.create(String.format("%s?sigunguCd=%s&bjdongCd=%s&ServiceKey=%s&_type=json",
                url, encodedSigunguCd, encodedBjdongCd, encodedServiceKey));

        // WebClient 호출
        String responseBody = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();



        openApiService.init(responseBody);

        returnAll.append(responseBody);

        return returnAll.toString();
    }


}
