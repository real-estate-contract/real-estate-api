package com.realEstate.realEstate.service;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Condominium;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.dto.WishDto;
//import com.realEstate.realEstate.model.dto.querydsl.PropertySearchCriteria;
import com.realEstate.realEstate.model.dto.querydsl.PropertySearchCriteria;
import com.realEstate.realEstate.model.entity.*;
import com.realEstate.realEstate.repository.AddressRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.repository.WishRepository;
import com.realEstate.realEstate.repository.cacheRepository.PropertyCacheRepository;
import com.realEstate.realEstate.repository.cacheRepository.UserCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PropertyCacheRepository redisRepository;
    private final UserCacheRepository userCacheRepository;
    private final WishRepository wishRepository;


    public Page<PropertyDto> searchProperties(Integer minPrice, Integer maxPrice, List<String> areaOptions, Integer minWeeklyFee,
                                              Integer maxWeeklyFee,
                                              Boolean parkingAvailable, Boolean airConditioner, Boolean washingMachine,
                                              Boolean refrigerator, Boolean includeManagementFee, LocalDate contractStartDate,
                                              LocalDate contractEndDate, Pageable pageable) {
        PropertySearchCriteria criteria = new PropertySearchCriteria(minPrice, maxPrice, areaOptions,
                minWeeklyFee, maxWeeklyFee, parkingAvailable, airConditioner, washingMachine, refrigerator,
                includeManagementFee, contractStartDate, contractEndDate);

        return propertyRepository.findAll(criteria.getPredicate(), pageable)
                .map(PropertyDto::from);
    }


    public PropertyDto detail(Long propertyId) {
        Property property = loadPropertyByPropertyId(propertyId);
        PropertyDto dto = PropertyDto.from(property);
        return dto;
    }

    @Transactional
    // Create
    public Long create(int weeklyFee, boolean deposit, int depositFee, int minimum,boolean washingmachine, boolean airconditioner, boolean refrigerator, int price, boolean management, int managementFee, int wholeFloor, int floor, boolean parkingAvailable,  LocalDate startDate,LocalDate endDate, Structure structure,boolean usageFee, boolean negotiationFee, boolean loanFund, int roomCount, int bathroomCount, int area1, int area2, Long addressId, String userName) {

        //user exit
        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));
        //address exit
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ApplicationException(ErrorCode.Address_NOT_FOUND, String.format("%s is not founded", addressId)));

//        redisRepository.setProperty(Property.of(transactionType, price, deposit, monthlyRent,management, managementFee, condominium, area, wholeFloor, floor, parkingAvailable, hasElevator,moveInDate,structure,direction, usageFee, negotiationFee, loanFund, year, generationCount,address, user));


        Property property  = propertyRepository.save(Property.of(weeklyFee, deposit, depositFee, price, management, managementFee,wholeFloor, floor, parkingAvailable, startDate,endDate,minimum, structure, usageFee, negotiationFee, loanFund, roomCount, bathroomCount, area1, area2, washingmachine, airconditioner, refrigerator,address, user));

        return property.getPropertyId();
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
//    @Transactional
//    public PropertyDto modify(CType transactionType, int price, int deposit, int monthlyRent, int area, int floor, boolean parkingAvailable, boolean hasElevator, LocalDate moveInDate, Structure structure, String userName, Long propertyId) {
//        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));
//
//        // property Exit
//        Property property = loadPropertyByPropertyId(propertyId);
//
//        // Address Exit
//
//        // 해당 유저가 적은게 맞는지
//        if (property.getUser() != user) {
//            throw new ApplicationException(ErrorCode.Invalid_Permission, String.format("%s has no permission", userName));
//        }
//
//        property.setTransactionType(transactionType);
//        property.setPrice(price);
//        property.setDeposit(deposit);
//        property.setMonthlyRent(monthlyRent);
//        property.setArea(area);
//        property.setFloor(floor);
//        property.setParkingAvailable(parkingAvailable);
//        property.setHasElevator(hasElevator);
//        property.setMoveInDate(moveInDate);
//        property.setStructure(structure);
//        property.setUser(user);
//
//
//        return PropertyDto.from(propertyRepository.saveAndFlush(property));
//    }
//    // Delete


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

    @Transactional
    public void wish(Long propertyId, String userName) {
        User user = userRepository.findByName(userName).orElseThrow(() ->
        {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음"); });
        Property property = loadPropertyByPropertyId(propertyId);

        wishRepository.findByUserAndProperty(user, property).ifPresent(it ->
        {throw new ApplicationException(ErrorCode.Invalid_Permission, "이미 찜하셨습니다."); });

        wishRepository.save(Wish.of(user, property));


    }

    @Transactional
    public Page<WishDto> myWishList(String userName, Pageable pageable) {

        User user = userRepository.findByName(userName).orElseThrow(() ->
        {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음"); });

        return wishRepository.findByUser(user,pageable).map(WishDto::from);

    }

    public Property loadPropertyByPropertyId(Long propertyId) {
        return propertyRepository.findById(propertyId).orElseThrow(()->
                        {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "매물 없음");}
        );

    }


}
