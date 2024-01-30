package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.constant.ManagementType;
import com.realEstate.realEstate.model.constant.ParkingOption;
import com.realEstate.realEstate.model.entity.PropertyCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.realEstate.realEstate.model.entity.PropertyCondition}
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PropertyConditionDto implements Serializable {
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
    boolean securityOffice;
    ManagementType managementType;
    boolean dispreferredFacilities;
    String dispreferredFacilitiesMemo;
    Long propertyId;

    public static PropertyConditionDto from(PropertyCondition entity) {
        return new PropertyConditionDto(
                entity.getId(),
                entity.getStreetL(),
                entity.getStreetR(),
                entity.isStreetPaving(),
                entity.isStreetAccessibility(),
                entity.getBusStation(),
                entity.isBusWalk(),
                entity.getBusTime(),
                entity.getSubwayStation(),
                entity.isSubwayWalk(),
                entity.getSubwayTime(),
                entity.getParkingOption(),
                entity.getParkingMemo(),
                entity.getElementarySchool(),
                entity.isElementaryWalk(),
                entity.getElementaryTime(),
                entity.getMiddleSchool(),
                entity.isMiddleWalk(),
                entity.getMiddleTime(),
                entity.getHighSchool(),
                entity.isHighWalk(),
                entity.getHighTime(),
                entity.getDepartmentStore(),
                entity.isDepartmentWalk(),
                entity.getDepartmentTime(),
                entity.getHospitalStore(),
                entity.isHospitalWalk(),
                entity.getHospitalTime(),
                entity.isSecurityOffice(),
                entity.getManagementType(),
                entity.isDispreferredFacilities(),
                entity.getDispreferredFacilitiesMemo(),
                entity.getProperty().getPropertyId()

        );
    }
}