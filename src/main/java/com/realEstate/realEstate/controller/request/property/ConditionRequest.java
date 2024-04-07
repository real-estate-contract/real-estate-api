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

    int streetL;
    int streetR;
    boolean streetPaving;
    boolean streetAccessibility;
    String busStation;
    boolean busWalk;
    int busTime;
    String subwayStation;
    boolean subwayWalk;
    int subwayTime;
    ParkingOption parkingOption;
    String parkingMemo;
    String elementarySchool;
    boolean elementaryWalk;
    int elementaryTime;
    String middleSchool;
    boolean middleWalk;
    int middleTime;
    String highSchool;
    boolean highWalk;
    int highTime;
    String departmentStore;
    boolean departmentWalk;
    int departmentTime;
    String hospitalStore;
    boolean hospitalWalk;
    int hospitalTime;
    String bank;
    boolean bankWalk;
    int bankTime;
    boolean securityOffice;
    ManagementType managementType;
    boolean dispreferredFacilities;
    String dispreferredFacilitiesMemo;

}
