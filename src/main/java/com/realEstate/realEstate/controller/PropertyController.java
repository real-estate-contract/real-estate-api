package com.realEstate.realEstate.controller;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.request.property.*;
import com.realEstate.realEstate.controller.response.Property.AddressResponse;
import com.realEstate.realEstate.controller.response.Property.PropertyResponse;
import com.realEstate.realEstate.controller.response.Response;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


@RestController
@Slf4j
@RequestMapping("/realEstate/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final UserRepository userRepository;
    private final AddressService addressService;
    private final DescriptionService descriptionService;
    private final PropertyOptionService optionService;
    private final PropertyImageService propertyImageService;

//    @PostMapping("/create")
//    public Response<Void> create(@RequestBody PropertyCreateRequest request, Authentication authentication) {
//        propertyService.create(request.getType(), request.getPrice(), request.getAddress(), request.getArea(), authentication.getName());
//
//        return Response.success();
//    }

    @PostMapping("/step1")
    public Response<AddressResponse> registerAddress(@RequestBody AddressCreateRequest request) {
        return Response.success(AddressResponse.fromDto(addressService.registerAddress(request.getStreetAddress(),request.getCity(), request.isOwner())));
    }

    //TODO : 유저 불러오는 부분 다시 확인
    @PostMapping("/step2/{addressId}")
    public Response<Void> createProperty(@RequestBody PropertyCreateRequest request, @PathVariable Long addressId, Authentication authentication) {
        User user = userRepository.findByName(authentication.getName()).orElseThrow(() -> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");
        });
        propertyService.create(request.getTransactionType(),request.getPrice(), request.getDeposit(), request.getMonthlyRent(), request.getArea(), request.getFloor(), request.isParkingAvailable(), request.isHasElevator(), request.getMoveInDate(),request.getStructure(),addressId, user.getName());
        return Response.success();
    }

    @PostMapping("/step3/{propertyId}")
    public Response<Void> registerOption(@RequestBody OptionCreateRequest request, @PathVariable Long propertyId) {
        optionService.registerOption(request.isSink(), request.isAirConditioner(), request.isShoeRack(), request.isWashingMachine(), request.isRefrigerator(), request.isWardrobe(), request.isGasRange(), request.isInduction(), request.isBed(), request.isDesk(), request.isMicrowave(), request.isBookshelf(), propertyId);
        return Response.success();
    }

    @PostMapping("step4/{propertyId}")
    public Response<Void> registerDescription(@RequestBody DescriptionCreateRequest request, @PathVariable Long propertyId) {
        descriptionService.registerDescription(request.getMemo(), request.isLoanAvailable(), request.isPetFriendly(), propertyId);
        return Response.success();
    }

    //TODO : step5 파일 업로
    @PostMapping("step5/{propertyId}")
    public Response<Void> uploadPropertyImages(@PathVariable Long propertyId, MultipartFile[] images){
        propertyImageService.uploadImage(propertyId, Arrays.asList(images));
        return Response.success();
    }

    @GetMapping("/list")
    public Response<Page<PropertyResponse>> list(Pageable pageable, Authentication authentication) {
        return Response.success(propertyService.list(pageable).map(PropertyResponse::fromDto));
    }

    @GetMapping("myList")
    public Response<Page<PropertyResponse>> myList(Pageable pageable, Authentication authentication) {
        return Response.success(propertyService.myList(authentication.getName(), pageable).map(PropertyResponse::fromDto));
    }

//    @PutMapping("/{propertyId}")
//    public Response<PropertyResponse> modify(@PathVariable Integer propertyId, @RequestBody PropertyModifyRequest request, Authentication authentication) {
//
//        PropertyDto propertyDto = propertyService.modify(request.getType(), request.getPrice(), request.getAddress(), request.getArea(), authentication.getName(), propertyId);
//
//        return Response.success(PropertyResponse.fromDto(propertyDto));
//    }

//    @DeleteMapping("/{propertyId}")
//    public Response<Void> delete(@PathVariable Integer propertyId, Authentication authentication) {
//        propertyService.delete(authentication.getName(), propertyId);
//        return Response.success();
//    }
}
