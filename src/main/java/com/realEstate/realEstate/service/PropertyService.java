package com.realEstate.realEstate.service;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.dto.WishDto;
import com.realEstate.realEstate.model.dto.querydsl.PropertySearchCriteria;
import com.realEstate.realEstate.model.entity.*;
import com.realEstate.realEstate.repository.AddressRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.repository.WishRepository;
import com.realEstate.realEstate.repository.cacheRepository.PropertyCacheRepository;
import com.realEstate.realEstate.repository.cacheRepository.UserCacheRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PropertyCacheRepository redisRepository;
    private final UserCacheRepository userCacheRepository;
    private final WishRepository wishRepository;

    public Page<PropertyDto> searchProperties(CType transactionType, Integer minPrice, Integer maxPrice,
                                              Integer minArea, Integer maxArea, Integer minFloor, Integer maxFloor,
                                              Structure structure, Boolean sink, Boolean airConditioner, Boolean shoeRack,
                                              Boolean washingMachine, Boolean refrigerator, Boolean wardrobe, Boolean gasRange,
                                              Boolean induction, Boolean bed, Boolean desk, Boolean microwave, Boolean bookshelf,
                                              Integer minDeposit, Integer maxDeposit, Integer minMonthlyRent, Integer maxMonthlyRent,
                                              Pageable pageable){
        PropertySearchCriteria criteria = new PropertySearchCriteria(transactionType, minPrice, maxPrice,
                minArea, maxArea, minFloor, maxFloor, structure, sink, airConditioner, shoeRack,
                washingMachine, refrigerator, wardrobe, gasRange, induction, bed, desk, microwave, bookshelf,minDeposit,maxDeposit,minMonthlyRent,maxMonthlyRent);

        return propertyRepository.findAll(criteria.getPredicate(), pageable).map(PropertyDto::from);


    }

    public PropertyDto detail(Long propertyId) {
        Property property = loadPropertyByPropertyId(propertyId);
        PropertyDto dto = PropertyDto.from(property);
        return dto;
    }

    @Transactional
    // Create
    public void create(CType transactionType, int price, int deposit,int monthlyRent,int managementFee, boolean condominium,int area, int floor, boolean parkingAvailable, boolean hasElevator, LocalDate moveInDate, Structure structure, String direction, Long addressId, String userName) {

        //user exit
        User user = userRepository.findByName(userName).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", userName)));
        //address exit
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ApplicationException(ErrorCode.Address_NOT_FOUND, String.format("%s is not founded", addressId)));

        redisRepository.setProperty(Property.of(transactionType, price, deposit, monthlyRent, managementFee, condominium, area, floor, parkingAvailable, hasElevator,moveInDate,structure,direction, address,user));


        propertyRepository.save(Property.of(transactionType, price, deposit, monthlyRent, managementFee, condominium, area, floor, parkingAvailable, hasElevator,moveInDate,structure,direction,address,user));
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
        return redisRepository.getProperty(propertyId).orElseGet(
                ()-> propertyRepository.findById(propertyId).orElseThrow(()->
                        {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "매물 없음");
                        })
        );

    }


}
