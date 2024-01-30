package com.realEstate.realEstate.controller.response.contract;

import com.realEstate.realEstate.model.dto.LandInfoDto;
import com.realEstate.realEstate.model.entity.BuildingInfo;
import com.realEstate.realEstate.model.entity.LandInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class LandInfoResponse {
    private String ldCodeNm; //법정동명
    private String lndcgrCodeNm; //지목명
    private String lndpclAr; //면적(㎡)

    public static LandInfoResponse fromDto(LandInfoDto dto) {
        return LandInfoResponse.builder()
                .ldCodeNm(dto.getLdCodeNm())
                .lndcgrCodeNm(dto.getLndcgrCodeNm())
                .lndpclAr(dto.getLndpclAr())
                .build();
    }
}
