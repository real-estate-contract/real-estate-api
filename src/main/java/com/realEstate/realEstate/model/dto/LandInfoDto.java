package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.BuildingInfo;
import com.realEstate.realEstate.model.entity.LandInfo;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LandInfoDto {
    private String ldCodeNm; //법정동명
    private String lndcgrCodeNm; //지목명
    private String lndpclAr; //면적(㎡)

    @Builder
    public LandInfoDto(String ldCodeNm, String lndcgrCodeNm, String lndpclAr) {
        this.ldCodeNm = ldCodeNm;
        this.lndcgrCodeNm = lndcgrCodeNm;
        this.lndpclAr = lndpclAr;
    }

    public static LandInfoDto fromEntity(LandInfo entity) {
        return LandInfoDto.builder()
                .ldCodeNm(entity.getLdCodeNm())
                .lndcgrCodeNm(entity.getLndcgrCodeNm())
                .lndpclAr(entity.getLndpclAr())
                .build();
    }

}
