package com.realEstate.realEstate.Controller.request.Property;

import com.realEstate.realEstate.model.constant.HType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public class propertyModifyRequest {

    private HType type;
    private BigDecimal price;
    private String address;
    private int area;
}
