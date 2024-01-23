package com.realEstate.realEstate.service.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realEstate.realEstate.controller.response.contract.BuildingInfoResponse;
import com.realEstate.realEstate.controller.response.contract.LandInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class LandInfoService {
    @Value("${api.land-key}")
    private String landkey;

    private final WebClient webClient;

    public LandInfoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<LandInfoResponse> getLandInfo(String pnu) throws IOException {
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

        // Parse the JSON response and return BuildingInfoResponse objects
        return parseLandInfoResponse(responseBody);
    }

    private List<LandInfoResponse> parseLandInfoResponse(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        JsonNode itemsNode = jsonNode.path("ladfrlVOList").path("ladfrlVOList");

        List<LandInfoResponse> landInfoList = new ArrayList<>();
        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                LandInfoResponse landInfoResponses = LandInfoResponse.builder()
                        .ldCodeNm(itemNode.path("ldCodeNm").asText())
                        .lndcgrCodeNm(itemNode.path("lndcgrCodeNm").asText())
                        .lndpclAr(itemNode.path("lndpclAr").asText())
                        .build();
                landInfoList.add(landInfoResponses);
            }
        }
        return landInfoList;
    }
}
