package com.realEstate.realEstate.service.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realEstate.realEstate.controller.response.contract.LandRightResponse;
import com.realEstate.realEstate.model.entity.LandRightInfo;
import com.realEstate.realEstate.repository.contract.LandRightInfoRespository;
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
public class LandRightInfoService {
    @Value("${api.land-key}")
    private String landkey;

    private LandRightInfoRespository landRightInfoRespository;
    private final WebClient webClient;

    public LandRightInfoService(WebClient webClient, LandRightInfoRespository landRightInfoRespository) {
        this.webClient = webClient;
        this.landRightInfoRespository = landRightInfoRespository;
    }

    public List<LandRightResponse> getLandInfo(String pnu) throws IOException {
        String url = "http://api.vworld.kr/ned/data/ldaregList";

        String encodedPnu = URLEncoder.encode(pnu, StandardCharsets.UTF_8.toString());
        String encodedServiceKey = URLEncoder.encode(landkey, StandardCharsets.UTF_8.toString());

        URI uri = URI.create(String.format("%s?pnu=%s&format=json&key=%s&domain=www.test.com/map.html",
                url, encodedPnu, encodedServiceKey));

        String responseBody = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return parseLandInfoResponse(responseBody);
    }

    private List<LandRightResponse> parseLandInfoResponse(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        JsonNode itemsNode = jsonNode.path("ldaregVOList").path("ldaregVOList");

        List<LandRightResponse> landRightInfoList = new ArrayList<>();

        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                LandRightInfo landRightInfo = LandRightInfo.builder()
                        .ldaQotaRate(itemNode.path("ldaQotaRate").asText())
                        .build();

                // Save the entity
                landRightInfoRespository.save(landRightInfo);

                // Convert the entity to response and add to the list if needed
                landRightInfoList.add(LandRightResponse.builder()
                        .ldaQotaRate(landRightInfo.getLdaQotaRate())
                        .build());
            }
        }
        return landRightInfoList;
    }
}
