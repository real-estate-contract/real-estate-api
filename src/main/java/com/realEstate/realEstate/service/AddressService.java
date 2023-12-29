package com.realEstate.realEstate.service;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.AddressDto;
import com.realEstate.realEstate.model.entity.Address;
import com.realEstate.realEstate.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    @Transactional
    public AddressDto registerAddress(String streetAddress, String city, boolean owner) {

        Address address = addressRepository.save(Address.of(streetAddress, city, owner));

        return AddressDto.from(address);
    }

    public AddressDto modifyAddress(Long addressId, String streetAddress, String city, boolean owner) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ApplicationException(ErrorCode.Address_NOT_FOUND, String.format("%s is not founded", addressId)));

        address.setStreetAddress(streetAddress);
        address.setCity(city);
        address.setOwner(owner);

        return AddressDto.from(address);
    }
}
