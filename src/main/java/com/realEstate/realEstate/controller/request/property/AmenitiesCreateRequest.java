package com.realEstate.realEstate.controller.request.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AmenitiesCreateRequest {

    String subway;
    String bus;
    String mart;
    String cafe;
    String laundry;
    String hospital;
    String bank;
}
