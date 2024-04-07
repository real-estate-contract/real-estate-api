package com.realEstate.realEstate.controller;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.request.property.*;
import com.realEstate.realEstate.controller.response.property.AddressResponse;
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
    private final DescriptionService descriptionService;
    private final PropertyOptionService optionService;
    private final PropertyImageService propertyImageService;
    private final PropertyAmenitiesService amenitiesService;
    private final PropertyConditionService propertyConditionService;


    @GetMapping({"/{propertyId}"})
    public Response<PropertyResponse> detail(@PathVariable Long propertyId) {
        return Response.success(PropertyResponse.fromDto(propertyService.detail(propertyId)));

    }



    @PostMapping("/step1")
    public Response<AddressResponse> registerAddress(@RequestBody AddressCreateRequest request) {
        return Response.success(AddressResponse.fromDto(addressService.registerAddress(request.getStreetAddress(),request.getCity(), request.isOwner())));
    }

    @PostMapping("/step2/{addressId}")
    public Response<Void> createProperty(@RequestBody PropertyCreateRequest request, @PathVariable Long addressId, Authentication authentication) {
        User user = userRepository.findByName(authentication.getName()).orElseThrow(() -> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");
        });
        propertyService.create(request.getTransactionType(),request.getPrice(), request.getDeposit(), request.getMonthlyRent(), request.isManagement(), request.getManagementFee(), request.getCondominium(),request.getArea(), request.getWholeFloor(), request.getFloor(), request.isParkingAvailable(), request.isHasElevator(), request.getMoveInDate(),request.getStructure(),request.getDirection(), request.isUsageFee(), request.isNegotiationFee(), request.isLoanFund(), addressId, user.getName());
        return Response.success();
    }

    @DeleteMapping("/{propertyId}")
    public Response<Void> deleteProperty(@PathVariable Long propertyId, Authentication authentication) {
        propertyService.delete(authentication.getName(), propertyId);
        return Response.success();
    }

    @PostMapping("/step3/{propertyId}")
    public Response<Void> registerOption(@RequestBody OptionCreateRequest request, @PathVariable Long propertyId) {
        optionService.registerOption(request.isSink(), request.isAirConditioner(), request.isShoeRack(), request.isWashingMachine(), request.isRefrigerator(), request.isWardrobe(), request.isGasRange(), request.isInduction(), request.isBed(), request.isDesk(), request.isMicrowave(), request.isBookshelf(), propertyId);
        return Response.success();
    }

    @PostMapping("step4/{propertyId}")
    public Response<Void> registerDescription(@RequestBody DescriptionCreateRequest request, @PathVariable Long propertyId) {
        descriptionService.registerDescription(request.getLineMemo(),request.getMemo(), request.isLoanAvailable(), request.isPetFriendly(), propertyId);
        return Response.success();
    }

    @PostMapping("step5/{propertyId}")
    public Response<Void> registerAmenities(@RequestBody AmenitiesCreateRequest request, @PathVariable Long propertyId) {
        amenitiesService.createAmenities(request.getSubway(), request.getBus(), request.getMart(), request.getCafe(), request.getLaundry(), request.getHospital(), request.getBank(), propertyId);
        return Response.success();
    }

    @PostMapping("step6/{propertyId}")
    public Response<Void> registerCondition(@RequestBody ConditionRequest request, @PathVariable Long propertyId) {
        propertyConditionService.createCondition(request.getStreetL(), request.getStreetR(), request.isStreetPaving(), request.isStreetAccessibility(), request.getBusStation(), request.isBusWalk(), request.getBusTime(), request.getSubwayStation(), request.isSubwayWalk(), request.getSubwayTime(), request.getParkingOption(), request.getParkingMemo(), request.getElementarySchool(), request.isElementaryWalk(), request.getElementaryTime(), request.getMiddleSchool(), request.isMiddleWalk(), request.getMiddleTime(), request.getHighSchool(), request.isHighWalk(), request.getHighTime(), request.getDepartmentStore(), request.isDepartmentWalk(), request.getDepartmentTime(), request.getHospitalStore(), request.isHospitalWalk(), request.getHospitalTime(),request.getBank(), request.isBankWalk(), request.getBankTime(), request.isSecurityOffice(), request.getManagementType(), request.isDispreferredFacilities(), request.getDispreferredFacilitiesMemo(), propertyId);
        return Response.success();
    }


    @PostMapping("step7/{propertyId}")
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
        CType transactionType = request.getTransactionType();
        Integer minPrice = request.getMinPrice();
        Integer maxPrice = request.getMaxPrice();
        Integer minArea = request.getMinArea();
        Integer maxArea = request.getMaxArea();
        Integer minFloor = request.getMinFloor();
        Integer maxFloor = request.getMaxFloor();
        Structure structure = request.getStructure();
        Boolean parkingAvailable = request.getParkingAvailable();
        Boolean sink = request.getSink();
        Boolean airConditioner = request.getAirConditioner();
        Boolean shoeRack = request.getShoeRack();
        Boolean washingMachine = request.getWashingMachine();
        Boolean refrigerator = request.getRefrigerator();
        Boolean wardrobe = request.getWardrobe();
        Boolean gasRange = request.getGasRange();
        Boolean induction = request.getInduction();
        Boolean bed = request.getBed();
        Boolean desk = request.getDesk();
        Boolean microwave = request.getMicrowave();
        Boolean bookshelf = request.getBookshelf();
        Integer minDeposit = request.getMinDeposit();
        Integer maxDeposit = request.getMaxDeposit();
        Integer minMonthlyRent = request.getMinMonthlyRent();
        Integer maxMonthlyRent = request.getMaxMonthlyRent();

        Page<PropertyResponse> properties = propertyService.searchProperties(
                transactionType, minPrice, maxPrice, minArea, maxArea, minFloor, maxFloor,
                structure,parkingAvailable, sink, airConditioner, shoeRack, washingMachine, refrigerator,
                wardrobe, gasRange, induction, bed, desk, microwave, bookshelf,
                minDeposit, maxDeposit, minMonthlyRent, maxMonthlyRent,  pageable).map(PropertyResponse::fromDto);

        return Response.success(properties);
    }


}
