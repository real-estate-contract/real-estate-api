package com.realEstate.realEstate.controller.request.property;

import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PropertySearchRequest {
    private CType transactionType;
    private Integer minPrice;
    private Integer maxPrice;
    private List<String> areaOptions;
    private List<String> floorOptions;
    private Structure structure;
    private Boolean parkingAvailable;
    private Boolean sink;
    private Boolean airConditioner;
    private Boolean shoeRack;
    private Boolean washingMachine;
    private Boolean refrigerator;
    private Boolean wardrobe;
    private Boolean gasRange;
    private Boolean induction;
    private Boolean bed;
    private Boolean desk;
    private Boolean microwave;
    private Boolean bookshelf;
    private Integer minDeposit;
    private Integer maxDeposit;
    private Integer minMonthlyRent;
    private Integer maxMonthlyRent;
    private Integer yearOfCompletionOption;
    private Integer totalUnitOption;
    private Boolean includeManagementFee;

}
