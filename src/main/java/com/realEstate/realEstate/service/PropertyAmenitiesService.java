package com.realEstate.realEstate.service;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.response.property.AmenitiesResponse;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.PropertyAmenities;
import com.realEstate.realEstate.repository.PropertyAmenitiesRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.cacheRepository.PropertyCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PropertyAmenitiesService {

    private final PropertyAmenitiesRepository propertyAmenitiesRepository;
    private final PropertyCacheRepository redisRepository;
    private final PropertyRepository propertyRepository;

    @Transactional
    public void createAmenities(String subway, String bus, String mart, String cafe, String laundry, String hospital, String bank, Long propertyId) {
        Property property = loadPropertyByPropertyId(propertyId);

        PropertyAmenities amenities = PropertyAmenities.of(subway, bus,  mart, cafe, laundry,  hospital, bank, property);
        propertyAmenitiesRepository.save(amenities);
        property.setAmenities(amenities);
    }

    //TODO: 수정 부분을 한 번에 어떻게 처리할 것 인가에 대한 고민

    public Property loadPropertyByPropertyId(Long propertyId) {
        return redisRepository.getProperty(propertyId).orElseGet(
                ()-> propertyRepository.findById(propertyId).orElseThrow(()->
                {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "매물 없음");
                })
        );

    }

}
