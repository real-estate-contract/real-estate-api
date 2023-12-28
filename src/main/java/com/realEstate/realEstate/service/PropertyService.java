package com.realEstate.realEstate.service;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;


    @Transactional
    // Create
    public void create(HType type, BigDecimal price, String address, int area, String userName) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));

        propertyRepository.save(Property.of(type,price,address,area,user));
    }

    // ReadAll(페이징 처리)
    // modify
    @Transactional
    public PropertyDto modify(HType type, BigDecimal price, String address, int area, String userName, Integer propertyId) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));

        // property Exit
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ApplicationException(ErrorCode.Property_NOT_FOUND, String.format("%s is not founded", propertyId)));

        // 해당 유저가 적은게 맞는지
        if (property.getUser() != user) {
            throw new ApplicationException(ErrorCode.Invalid_Permission, String.format("%s has no permission", userName));
        }

        property.setType(type);
        property.setPrice(price);
        property.setAddress(address);
        property.setArea(area);


        return PropertyDto.from(propertyRepository.saveAndFlush(property));
    }
    // Delete

}
