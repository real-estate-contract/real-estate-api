package com.realEstate.realEstate.service;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.entity.Address;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.AddressRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.repository.cacheRepository.PropertyCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final PropertyCacheRepository redisRepository;

    public PropertyDto detail(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .map(PropertyDto::from)
                .orElseThrow(() -> {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "없음"); });
    }

    @Transactional
    // Create
    public void create(CType transactionType, int price, int deposit, int monthlyRent, int area, int floor, boolean parkingAvailable, boolean hasElevator, LocalDate moveInDate, Structure structure, Long addressId, String userName) {

        //user exit
        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));
        //address exit
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ApplicationException(ErrorCode.Address_NOT_FOUND, String.format("%s is not founded", addressId)));

        redisRepository.setProperty(Property.of(transactionType, price, deposit, monthlyRent, area, floor, parkingAvailable, hasElevator,moveInDate,structure,address,user));


        propertyRepository.save(Property.of(transactionType, price, deposit, monthlyRent, area, floor, parkingAvailable, hasElevator,moveInDate,structure,address,user));
    }

    // ReadAll(페이징 처리)
    public Page<PropertyDto> list(Pageable pageable) {
        return propertyRepository.findAll(pageable).map(PropertyDto::from);
    }

    // 내가 올린 매물만 보기
    public Page<PropertyDto> myList(String userName, Pageable pageable) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));

        return propertyRepository.findAllByUser(user,pageable).map(PropertyDto::from);
    }


    // modify
    @Transactional
    public PropertyDto modify(CType transactionType, int price, int deposit, int monthlyRent, int area, int floor, boolean parkingAvailable, boolean hasElevator, LocalDate moveInDate, Structure structure, String userName, Long propertyId) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));

        // property Exit
        Property property = loadPropertyByPropertyId(propertyId);

        // Address Exit

        // 해당 유저가 적은게 맞는지
        if (property.getUser() != user) {
            throw new ApplicationException(ErrorCode.Invalid_Permission, String.format("%s has no permission", userName));
        }

        property.setTransactionType(transactionType);
        property.setPrice(price);
        property.setDeposit(deposit);
        property.setMonthlyRent(monthlyRent);
        property.setArea(area);
        property.setFloor(floor);
        property.setParkingAvailable(parkingAvailable);
        property.setHasElevator(hasElevator);
        property.setMoveInDate(moveInDate);
        property.setStructure(structure);
        property.setUser(user);


        return PropertyDto.from(propertyRepository.saveAndFlush(property));
    }
    // Delete


    @Transactional
    public void delete(String userName, Long propertyId) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));

        // property Exit
        Property property = loadPropertyByPropertyId(propertyId);

        // 해당 유저가 적은게 맞는지
        if (property.getUser() != user) {
            throw new ApplicationException(ErrorCode.Invalid_Permission, String.format("%s has no permission", userName));
        }

        propertyRepository.delete(property);
    }

    public Property loadPropertyByPropertyId(Long propertyId) {
        return redisRepository.getProperty(propertyId).orElseGet(
                ()-> propertyRepository.findById(propertyId).orElseThrow(()->
                        {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "매물 없음");
                        })
        );

    }
}
