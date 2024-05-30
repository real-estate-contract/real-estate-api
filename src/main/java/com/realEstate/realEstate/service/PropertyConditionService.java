package com.realEstate.realEstate.service;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.ManagementType;
import com.realEstate.realEstate.model.constant.ParkingOption;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.PropertyCondition;
import com.realEstate.realEstate.repository.PropertyConditionRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.cacheRepository.PropertyCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PropertyConditionService {

    private final PropertyCacheRepository redisRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyConditionRepository propertyConditionRepository;

    @Transactional
    public void createCondition(String limeMemo, String memo, int streetL, int streetR, boolean streetPaving, String busStation, boolean busWalk, int busTime, String subwayStation, boolean subwayWalk, int subwayTime, ParkingOption parkingOption, String parkingMemo, String departmentStore, boolean departmentWalk, int departmentTime, String hospitalStore, boolean hospitalWalk, int hospitalTime,   Long propertyId) {
        Property property = loadPropertyByPropertyId(propertyId);
        PropertyCondition propertyCondition = PropertyCondition.of(limeMemo, memo, streetL, streetR,streetPaving,  busStation, busWalk, busTime, subwayStation, subwayWalk, subwayTime, parkingOption, parkingMemo, departmentStore, departmentWalk, departmentTime, hospitalStore,hospitalWalk, hospitalTime,  property);
        property.setPropertyCondition(propertyCondition);
        propertyConditionRepository.save(propertyCondition);
    }


    public Property loadPropertyByPropertyId(Long propertyId) {
        return propertyRepository.findById(propertyId).orElseThrow(()->
                {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "매물 없음");}
        );

    }
}
