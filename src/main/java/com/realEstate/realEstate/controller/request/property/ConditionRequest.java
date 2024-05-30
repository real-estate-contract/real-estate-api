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

    String limeMemo;
    String memo;
    int streetL;
    int streetR;
    boolean streetPaving;
    String busStation;
    boolean busWalk;
    int busTime;
    String subwayStation;
    boolean subwayWalk;
    int subwayTime;
    ParkingOption parkingOption;
    String parkingMemo;
    String departmentStore;
    boolean departmentWalk;
    int departmentTime;
    String hospitalStore;
    boolean hospitalWalk;
    int hospitalTime;


}
