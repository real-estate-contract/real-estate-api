package com.realEstate.realEstate.controller;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.request.property.*;
import com.realEstate.realEstate.controller.response.property.AddressResponse;
import com.realEstate.realEstate.controller.response.property.PropertyConditionResponse;
import com.realEstate.realEstate.controller.response.property.PropertyCreateResponse;
import com.realEstate.realEstate.controller.response.property.PropertyResponse;
import com.realEstate.realEstate.controller.response.Response;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.dto.PropertyImageDto;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/realEstate/property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyRepository propertyRepository;

    private final PropertyService propertyService;
    private final UserRepository userRepository;
    private final AddressService addressService;
    private final PropertyImageService propertyImageService;
    private final PropertyConditionService propertyConditionService;


    @GetMapping({"/{propertyId}"})
    public Response<PropertyResponse> detail(@PathVariable Long propertyId) {
        return Response.success(PropertyResponse.fromDto(propertyService.detail(propertyId)));

    }



    @PostMapping("/step1")
    public Response<AddressResponse> registerAddress(@RequestBody AddressCreateRequest request) {
        return Response.success(AddressResponse.fromDto(addressService.registerAddress(request.getStreetAddress())));
    }

    @PostMapping("/step2/{addressId}")
    public Response<PropertyCreateResponse> createProperty(@RequestBody PropertyCreateRequest request, @PathVariable Long addressId, Authentication authentication) {
        User user = userRepository.findByName(authentication.getName()).orElseThrow(() -> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");
        });
        return Response.success(PropertyCreateResponse.fromDto(propertyService.create(request.getWeeklyFee(), request.isDeposit(),request.getDepositFee(), request.getMinimum(), request.isWashingmachine(), request.isAirconditioner(), request.isRefrigerator(), request.getPrice(), request.isManagement(), request.getManagementFee(), request.getWholeFloor(), request.getFloor(), request.isParkingAvailable(), request.getStartDate(),request.getEndDate(),request.getStructure(), request.isUsageFee(), request.isNegotiationFee(), request.isLoanFund(), request.getRoomCount(), request.getBathroomCount(), request.getArea1(), request.getArea2(), addressId, user.getName())));
    }

    @DeleteMapping("/{propertyId}")
    public Response<Void> deleteProperty(@PathVariable Long propertyId, Authentication authentication) {
        propertyService.delete(authentication.getName(), propertyId);
        return Response.success();
    }


    @PostMapping("step3/{propertyId}")
    public Response<PropertyConditionResponse> registerCondition(@RequestBody ConditionRequest request, @PathVariable Long propertyId) {

        return Response.success(PropertyConditionResponse.fromDto(propertyConditionService.createCondition(request.getLimeMemo(), request.getMemo(), request.getStreetL(), request.getStreetR(), request.isStreetPaving(), request.getBusStation(), request.isBusWalk(), request.getBusTime(), request.getSubwayStation(), request.isSubwayWalk(), request.getSubwayTime(), request.getParkingOption(), request.getParkingMemo(),  propertyId)));
    }


    @PostMapping("step4/{propertyId}")
    public Response<Void> uploadPropertyImages(@PathVariable Long propertyId, @RequestParam("images") List<MultipartFile> images) {

        PropertyImageDto propertyImageDto = new PropertyImageDto();
        propertyImageDto.setImages(images);
        propertyImageService.uploadImage(propertyId, propertyImageDto);
        return Response.success();
    }

    @PostMapping("/wish/{propertyId}")
    public Response<Void> wish(@PathVariable Long propertyId, Authentication authentication) {
        propertyService.wish(propertyId, authentication.getName());
        return Response.success();
    }

    @GetMapping("/myWishList")
    public Response<Page<PropertyResponse>> myWishList(Authentication authentication, Pageable pageable) {

        return Response.success(propertyService.myWishList(authentication.getName(), pageable).map(PropertyResponse::from));

    }

    @GetMapping("/list")
    public Response<Page<PropertyResponse>> list(Pageable pageable, Authentication authentication) {

        return Response.success(propertyService.list(pageable).map(PropertyResponse::fromDto));
    }

    @GetMapping("myList")
    public Response<Page<PropertyResponse>> myList(Pageable pageable, Authentication authentication) {
        return Response.success(propertyService.myList(authentication.getName(), pageable).map(PropertyResponse::fromDto));
    }


    @GetMapping("/search")
    public Response<Page<PropertyResponse>> searchProperties(PropertySearchRequest request, Pageable pageable) {
        Integer minPrice = request.getMinPrice();
        Integer maxPrice = request.getMaxPrice();
        List<String> areaOptions = request.getAreaOptions();
        Integer minWeeklyFee = request.getMinWeeklyFee();
        Integer maxWeeklyFee = request.getMaxWeeklyFee();
        Boolean parkingAvailable = request.getParkingAvailable();
        Boolean airConditioner = request.getAirConditioner();
        Boolean washingMachine = request.getWashingMachine();
        Boolean refrigerator = request.getRefrigerator();
        Boolean includeManagementFee = request.getIncludeManagementFee();
        LocalDate contractStartDate = request.getContractStartDate();
        LocalDate contractEndDate = request.getContractEndDate();

        Page<PropertyResponse> properties = propertyService.searchProperties(
                        minPrice, maxPrice, areaOptions, minWeeklyFee, maxWeeklyFee, parkingAvailable,
                        airConditioner, washingMachine, refrigerator, includeManagementFee,
                        contractStartDate, contractEndDate, pageable)
                .map(PropertyResponse::fromDto);

        return Response.success(properties);
    }

}
