package com.realEstate.realEstate.service.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realEstate.realEstate.controller.response.contract.LandPriceResponse;
import com.realEstate.realEstate.model.entity.LandPriceInfo;
import com.realEstate.realEstate.repository.contract.LandPriceInfoRespository;
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
public class LandPriceInfoService {
    @Value("${api.land-key}")
    private String landkey;

    private LandPriceInfoRespository landPriceInfoRespository;
    private final WebClient webClient;

    public LandPriceInfoService(WebClient webClient, LandPriceInfoRespository landPriceInfoRespository) {
        this.webClient = webClient;
        this.landPriceInfoRespository = landPriceInfoRespository;
    }

    public List<LandPriceResponse> getLandInfo(String pnu) throws IOException {
        String url = "https://api.vworld.kr/ned/data/getIndvdLandPriceAttr";

        String encodedPnu = URLEncoder.encode(pnu, StandardCharsets.UTF_8.toString());
        String encodedServiceKey = URLEncoder.encode(landkey, StandardCharsets.UTF_8.toString());

        URI uri = URI.create(String.format("%s?pnu=%s&format=json&key=%s&pageNo=1&domain=www.test.com/map.html",
                url, encodedPnu, encodedServiceKey));

        String responseBody = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return parseLandInfoResponse(responseBody);
    }

    private List<LandPriceResponse> parseLandInfoResponse(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        JsonNode itemsNode = jsonNode.path("indvdLandPrices").path("field");

        List<LandPriceResponse> landPriceInfoList = new ArrayList<>();

        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                LandPriceInfo landPriceInfo = LandPriceInfo.builder()
                        .pblntfPclnd(itemNode.path("pblntfPclnd").asText())
                        .build();

                // Save the entity
                landPriceInfoRespository.save(landPriceInfo);

                // Convert the entity to response and add to the list if needed
                landPriceInfoList.add(LandPriceResponse.builder()
                        .pblntfPclnd(landPriceInfo.getPblntfPclnd())
                        .build());
            }
        }
        return landPriceInfoList;
    }
}
