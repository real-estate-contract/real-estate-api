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

    public static PropertyConditionDto from(PropertyCondition entity) {
        return new PropertyConditionDto(
                entity.getId(),
                entity.getLineMemo(),
                entity.getMemo(),
                entity.getStreetL(),
                entity.getStreetR(),
                entity.isStreetPaving(),
                entity.getBusStation(),
                entity.isBusWalk(),
                entity.getBusTime(),
                entity.getSubwayStation(),
                entity.isSubwayWalk(),
                entity.getSubwayTime(),
                entity.getParkingOption(),
                entity.getParkingMemo(),
                entity.getDepartmentStore(),
                entity.isDepartmentWalk(),
                entity.getDepartmentTime(),
                entity.getHospitalStore(),
                entity.isHospitalWalk(),
                entity.getHospitalTime(),
                entity.getProperty().getPropertyId()

        );
    }
}