package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.BuildingInfo;
import com.realEstate.realEstate.model.entity.LandInfo;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LandInfoDto {
    private String ldCodeNm; //법정동명
    private String lndcgrCodeNm; //지목명
    private String lndpclAr; //면적(㎡)

    //Dto -> Entity
    public LandInfo toEntity(){
        return LandInfo.builder()
                .ldCodeNm(ldCodeNm)
                .lndcgrCodeNm(lndcgrCodeNm)
                .lndpclAr(lndpclAr)
                .build();
    }
}
