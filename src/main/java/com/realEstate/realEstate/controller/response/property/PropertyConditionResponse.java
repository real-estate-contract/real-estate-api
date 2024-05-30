package com.realEstate.realEstate.controller.response.property;


import com.realEstate.realEstate.model.constant.ManagementType;
import com.realEstate.realEstate.model.constant.ParkingOption;
import com.realEstate.realEstate.model.dto.PropertyConditionDto;
import com.realEstate.realEstate.model.entity.PropertyCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropertyConditionResponse {

    Long id;
    String lineMemo;
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
    Long propertyId;

    public static PropertyConditionResponse fromDto(PropertyConditionDto dto) {
        return new PropertyConditionResponse(
                dto.getId(),
                dto.getLineMemo(),
                dto.getMemo(),
                dto.getStreetL(),
                dto.getStreetR(),
                dto.isStreetPaving(),
                dto.getBusStation(),
                dto.isBusWalk(),
                dto.getBusTime(),
                dto.getSubwayStation(),
                dto.isSubwayWalk(),
                dto.getSubwayTime(),
                dto.getParkingOption(),
                dto.getParkingMemo(),
                dto.getDepartmentStore(),
                dto.isDepartmentWalk(),
                dto.getDepartmentTime(),
                dto.getHospitalStore(),
                dto.isHospitalWalk(),
                dto.getHospitalTime(),
                dto.getPropertyId()

        );
    }
}
