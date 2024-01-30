package com.realEstate.realEstate.controller.response.contract;

import com.realEstate.realEstate.model.dto.BuildingInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class LandRightResponse {
    private String ldaQotaRate;
}
