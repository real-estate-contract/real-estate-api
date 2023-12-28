package com.realEstate.realEstate.Controller;


import com.realEstate.realEstate.Controller.request.Property.propertyCreateRequest;
import com.realEstate.realEstate.Controller.request.Property.propertyModifyRequest;
import com.realEstate.realEstate.Controller.response.Property.PropertyResponse;
import com.realEstate.realEstate.Controller.response.Response;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/realEstate/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final UserRepository userRepository;

    @PostMapping("/create")
    public Response<Void> create(@RequestBody propertyCreateRequest request, Authentication authentication) {
        propertyService.create(request.getType(), request.getPrice(), request.getAddress(), request.getArea(), authentication.getName());

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

    @PutMapping("/{propertyId}")
    public Response<PropertyResponse> modify(@PathVariable Integer propertyId, @RequestBody propertyModifyRequest request, Authentication authentication) {

        PropertyDto propertyDto = propertyService.modify(request.getType(), request.getPrice(), request.getAddress(), request.getArea(), authentication.getName(), propertyId);

        return Response.success(PropertyResponse.fromDto(propertyDto));
    }

    @DeleteMapping("/{propertyId}")
    public Response<Void> delete(@PathVariable Integer propertyId, Authentication authentication) {
        propertyService.delete(authentication.getName(), propertyId);
        return Response.success();
    }
}
