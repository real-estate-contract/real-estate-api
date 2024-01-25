package com.realEstate.realEstate.service.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realEstate.realEstate.controller.response.contract.BuildingInfoResponse;
import com.realEstate.realEstate.controller.response.contract.LandInfoResponse;
import com.realEstate.realEstate.model.entity.BuildingInfo;
import com.realEstate.realEstate.repository.contract.BuildingInfoRespository;
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
public class BuildingInfoService {

    @Value("${api.building-key}")
    private String buildingkey;

    private final WebClient webClient;
    private BuildingInfoRespository buildingInfoRespository;
    public BuildingInfoService(WebClient webClient, BuildingInfoRespository buildingInfoRespository) {
        this.webClient = webClient;
        this.buildingInfoRespository = buildingInfoRespository;
    }

    public List<BuildingInfoResponse> getBuildingInfo(String sigunguCd, String bjdongCd) throws IOException {
        String url = "http://apis.data.go.kr/1613000/BldRgstService_v2/getBrBasisOulnInfo";

        String encodedSigunguCd = URLEncoder.encode(sigunguCd, StandardCharsets.UTF_8.toString());
        String encodedBjdongCd = URLEncoder.encode(bjdongCd, StandardCharsets.UTF_8.toString());
        String encodedServiceKey = URLEncoder.encode(buildingkey, StandardCharsets.UTF_8.toString());

        URI uri = URI.create(String.format("%s?sigunguCd=%s&bjdongCd=%s&platGbCd=0&ServiceKey=%s&_type=json",
                url, encodedSigunguCd, encodedBjdongCd, encodedServiceKey));

        String responseBody = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Parse the JSON response and return BuildingInfoResponse objects
        return parseBuildingInfoResponse(responseBody);
    }

    private List<BuildingInfoResponse> parseBuildingInfoResponse(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        JsonNode itemsNode = jsonNode.path("response").path("body").path("items").path("item");

        List<BuildingInfoResponse> buildingInfoList = new ArrayList<>();

        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                BuildingInfo buildingInfo = BuildingInfo.builder()
                        .mainPurpsCdNm(itemNode.path("regstrGbCdNm").asText())
                        .etcRoof(itemNode.path("etcRoof").asText())
                        .useAprDay(itemNode.path("useAprDay").asText())
                        .newPlatPlc(itemNode.path("newPlatPlc").asText())
                        .archArea(itemNode.path("archArea").asInt())
                        .bcRat(itemNode.path("bcRat").asInt())
                        .vlRat(itemNode.path("vlRat").asInt())
                        .strctCdNm(itemNode.path("strctCdNm").asText())
                        .build();
                //save the entity
                buildingInfoRespository.save(buildingInfo);

                // Convert the entity to response and add to the list if needed
                buildingInfoList.add(BuildingInfoResponse.builder()
                        .mainPurpsCdNm(buildingInfo.getMainPurpsCdNm())
                        .etcRoof(buildingInfo.getEtcRoof())
                        .useAprDay(buildingInfo.getUseAprDay())
                        .newPlatPlc(buildingInfo.getNewPlatPlc())
                        .archArea(buildingInfo.getArchArea())
                        .bcRat(buildingInfo.getBcRat())
                        .vlRat(buildingInfo.getVlRat())
                        .strctCdNm(buildingInfo.getStrctCdNm())
                        .build());
            }
        }
        return buildingInfoList;
    }
}