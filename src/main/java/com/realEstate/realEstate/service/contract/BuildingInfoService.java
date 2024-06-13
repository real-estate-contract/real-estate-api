package com.realEstate.realEstate.service.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realEstate.realEstate.controller.response.contract.BuildingInfoResponse;
import com.realEstate.realEstate.model.entity.BuildingInfo;
import com.realEstate.realEstate.repository.contract.BuildingInfoRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuildingInfoService {

    @Value("${api.building-key}")
    private String buildingKey;

    private final WebClient webClient;
    private final BuildingInfoRespository buildingInfoRespository;
    private final ObjectMapper objectMapper;

    public List<BuildingInfoResponse> getBuildingInfo(String sigunguCd, String bjdongCd) throws IOException {
        String url = "http://apis.data.go.kr/1613000/BldRgstService_v2/getBrBasisOulnInfo";

        String encodedSigunguCd = URLEncoder.encode(sigunguCd, StandardCharsets.UTF_8.toString());
        String encodedBjdongCd = URLEncoder.encode(bjdongCd, StandardCharsets.UTF_8.toString());
        String encodedServiceKey = URLEncoder.encode(buildingKey, StandardCharsets.UTF_8.toString());

        URI uri = URI.create(String.format("%s?sigunguCd=%s&bjdongCd=%s&platGbCd=0&ServiceKey=%s&_type=json",
                url, encodedSigunguCd, encodedBjdongCd, encodedServiceKey));

        ClientResponse clientResponse = webClient.get()
                .uri(uri)
                .exchange()
                .block();

        String contentType = clientResponse.headers().contentType().orElseThrow().toString();
        String responseBody = clientResponse.bodyToMono(String.class).block();

        if (contentType.contains("application/json")) {
            return parseJsonResponse(responseBody);
        } else if (contentType.contains("application/xml") || contentType.contains("text/xml")) {
            return parseXmlResponse(responseBody);
        } else {
            throw new RuntimeException("Unsupported response type: " + contentType);
        }
    }

    private List<BuildingInfoResponse> parseJsonResponse(String responseBody) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        JsonNode itemsNode = jsonNode.path("response").path("body").path("items").path("item");

        List<BuildingInfoResponse> buildingInfoList = new ArrayList<>();
        log.info("건축물대장 api={}",buildingInfoList);

        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                BuildingInfo buildingInfo = BuildingInfo.builder()
                        .mainPurpsCdNm(itemNode.path("mainPurpsCdNm").asText())
                        .build();
                buildingInfoRespository.save(buildingInfo);
                buildingInfoList.add(BuildingInfoResponse.builder()
                        .mainPurpsCdNm(buildingInfo.getMainPurpsCdNm())
                        .build());
            }
        }
        return buildingInfoList;
    }

    private List<BuildingInfoResponse> parseXmlResponse(String responseBody) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(responseBody)));

            // Example of XML parsing, needs adjustment based on actual XML structure
            List<BuildingInfoResponse> buildingInfoList = new ArrayList<>();


            // Example parsing, adjust based on the actual XML structure
            NodeList itemList = document.getElementsByTagName("item");
            for (int i = 0; i < itemList.getLength(); i++) {
                Element item = (Element) itemList.item(i);

                BuildingInfo buildingInfo = BuildingInfo.builder()
                        .mainPurpsCdNm(item.getElementsByTagName("mainPurpsCdNm").item(0).getTextContent())
                        .build();
                buildingInfoRespository.save(buildingInfo);

                buildingInfoList.add(BuildingInfoResponse.builder()
                        .mainPurpsCdNm(buildingInfo.getMainPurpsCdNm())
                        .build());
            }

            return buildingInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse XML response", e);
        }
    }
}
