package com.realEstate.realEstate.controller.request.property;

import com.realEstate.realEstate.model.constant.HType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public class PropertyModifyRequest {

    private HType type;
    private BigDecimal price;
    private String address;
    private int area;
}
