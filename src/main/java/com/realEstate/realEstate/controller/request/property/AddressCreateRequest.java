package com.realEstate.realEstate.controller.request.property;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressCreateRequest {

    String streetAddress;
    String city;
    boolean owner;
}
