package com.realEstate.realEstate.controller.request.property;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DescriptionCreateRequest {

    String memo;
    boolean loanAvailable;
    boolean petFriendly;
}
