package com.realEstate.realEstate.controller.request.property;


import com.realEstate.realEstate.model.constant.ManagementType;
import com.realEstate.realEstate.model.constant.ParkingOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConditionRequest {

    String limeMemo; // 한문장
    String memo; // 상세설명
    int streetL; //왼
    int streetR; //오
    boolean streetPaving; // 포 /비
    String busStation;
    boolean busWalk;
    int busTime;
    String subwayStation;
    boolean subwayWalk;
    int subwayTime;
    ParkingOption parkingOption;
    String parkingMemo;


}
