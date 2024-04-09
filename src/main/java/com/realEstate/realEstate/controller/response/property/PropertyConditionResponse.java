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
    Long propertyId;

    public static PropertyConditionResponse fromDto(PropertyConditionDto dto) {
        return new PropertyConditionResponse(
                dto.getId(),
                dto.getStreetL(),
                dto.getStreetR(),
                dto.isStreetPaving(),
                dto.isStreetAccessibility(),
                dto.getBusStation(),
                dto.isBusWalk(),
                dto.getBusTime(),
                dto.getSubwayStation(),
                dto.isSubwayWalk(),
                dto.getSubwayTime(),
                dto.getParkingOption(),
                dto.getParkingMemo(),
                dto.getElementarySchool(),
                dto.isElementaryWalk(),
                dto.getElementaryTime(),
                dto.getMiddleSchool(),
                dto.isMiddleWalk(),
                dto.getMiddleTime(),
                dto.getHighSchool(),
                dto.isHighWalk(),
                dto.getHighTime(),
                dto.getDepartmentStore(),
                dto.isDepartmentWalk(),
                dto.getDepartmentTime(),
                dto.getHospitalStore(),
                dto.isHospitalWalk(),
                dto.getHospitalTime(),
                dto.getBank(),
                dto.isBankWalk(),
                dto.getBankTime(),
                dto.isSecurityOffice(),
                dto.getManagementType(),
                dto.isDispreferredFacilities(),
                dto.getDispreferredFacilitiesMemo(),
                dto.getPropertyId()

        );
    }
}
