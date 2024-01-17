package com.realEstate.realEstate.service.fetchData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.realEstate.exception.BuildingNotFoundException;
import com.realEstate.realEstate.controller.response.contract.BuildingOpenApiResponse;
import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Service
public class FetchContractService {
    @Value("${GET_BUILDING_API_KEY}")
    private String serviceKey;
    private final ContractRepository contractRepository;

    public void getBuildingLedger(Contract contract) throws JsonProcessingException{
        String sigunguCd = contract.getSigunguCd();
        String bjdongCd = contract.getBjdongCd();
        final String url = String.valueOf(UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("apis.data.go.kr")
                .path("/1613000/BldRgstService_v2")
                .queryParam("sigunguCd",sigunguCd)
                .queryParam("bjdongCd",bjdongCd)
                .build());
        System.out.println(url);
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        final WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(url)
                .build();

        String response = webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject jsonObject = XML.toJSONObject(response);
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        BuildingOpenApiResponse json = mapper.readValue(jsonObject.toString(), BuildingOpenApiResponse.class);
        if (json.getProtocol() == null) {
            throw new BuildingNotFoundException("주가 정보가 없습니다.");
        }


    }
}