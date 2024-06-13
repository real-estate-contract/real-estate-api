package com.realEstate.realEstate.controller.request.property;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PropertySearchRequest {
    private Integer minPrice;                // 최소 매물 가격
    private Integer maxPrice;                // 최대 매물 가격
    private Integer minWeeklyFee;            // 최소 주차 임대료
    private Integer maxWeeklyFee;            // 최대 주차 임대료
    private Boolean parkingAvailable;        // 주차 가능 여부
    private Boolean airConditioner;          // 에어컨 여부
    private Boolean washingMachine;          // 세탁기 여부
    private Boolean refrigerator;            // 냉장고 여부
    private Boolean includeManagementFee;    // 관리비 포함 여부
    private LocalDate contractStartDate;     // 계약 시작 날짜
    private LocalDate contractEndDate;       // 계약 종료 날짜
    private List<String> areaOptions;        // 평형수

}
