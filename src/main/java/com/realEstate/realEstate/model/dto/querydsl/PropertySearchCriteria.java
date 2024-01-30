package com.realEstate.realEstate.model.dto.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import com.realEstate.realEstate.model.entity.QProperty;

public class PropertySearchCriteria {
    private final BooleanExpression predicate;

    public PropertySearchCriteria(CType transactionType, Integer minPrice, Integer maxPrice,
                                  Integer minArea, Integer maxArea, Integer minFloor, Integer maxFloor,
                                  Structure structure, Boolean sink, Boolean airConditioner, Boolean shoeRack,
                                  Boolean washingMachine, Boolean refrigerator, Boolean wardrobe, Boolean gasRange,
                                  Boolean induction, Boolean bed, Boolean desk, Boolean microwave, Boolean bookshelf,
                                  Integer minDeposit, Integer maxDeposit, Integer minMonthlyRent, Integer maxMonthlyRent) {
        QProperty property = QProperty.property;

        BooleanExpression transactionTypeExpression = transactionType == null ? property.transactionType.isNotNull() :
                property.transactionType.eq(transactionType);

        BooleanExpression structureExpression = structure == null ? property.structure.isNotNull() :
                property.structure.eq(structure);

        BooleanExpression sinkExpression = sink == null ? property.option.sink.isNotNull() :
                property.option.sink.eq(sink);

        BooleanExpression airConditionerExpression = airConditioner == null ? property.option.airConditioner.isNotNull() :
                property.option.airConditioner.eq(airConditioner);

        BooleanExpression shoeRackExpression = shoeRack == null ? property.option.shoeRack.isNotNull() :
                property.option.shoeRack.eq(shoeRack);

        BooleanExpression washingMachineExpression = washingMachine == null ? property.option.washingMachine.isNotNull() :
                property.option.washingMachine.eq(washingMachine);

        BooleanExpression refrigeratorExpression = refrigerator == null ? property.option.refrigerator.isNotNull() :
                property.option.refrigerator.eq(refrigerator);

        BooleanExpression wardrobeExpression = wardrobe == null ? property.option.wardrobe.isNotNull() :
                property.option.wardrobe.eq(wardrobe);

        BooleanExpression gasRangeExpression = gasRange == null ? property.option.gasRange.isNotNull() :
                property.option.gasRange.eq(gasRange);

        BooleanExpression inductionExpression = induction == null ? property.option.induction.isNotNull() :
                property.option.induction.eq(induction);

        BooleanExpression bedExpression = bed == null ? property.option.bed.isNotNull() :
                property.option.bed.eq(bed);

        BooleanExpression deskExpression = desk == null ? property.option.desk.isNotNull() :
                property.option.desk.eq(desk);

        BooleanExpression microwaveExpression = microwave == null ? property.option.microwave.isNotNull() :
                property.option.microwave.eq(microwave);

        BooleanExpression bookshelfExpression = bookshelf == null ? property.option.bookshelf.isNotNull() :
                property.option.bookshelf.eq(bookshelf);

        BooleanExpression minPriceExpression = transactionType == CType.SALE && minPrice != null ?
                property.price.goe(minPrice) : null;

        BooleanExpression maxPriceExpression = transactionType == CType.SALE && maxPrice != null ?
                property.price.loe(maxPrice) : null;

        BooleanExpression depositExpression = minDeposit == null && maxDeposit == null ?
                property.deposit.isNotNull() : property.deposit.between(minDeposit, maxDeposit);

        BooleanExpression monthlyRentExpression = minMonthlyRent == null && maxMonthlyRent == null ?
                property.monthlyRent.isNotNull() : property.monthlyRent.between(minMonthlyRent, maxMonthlyRent);

        this.predicate = transactionTypeExpression
                .and(structureExpression)
                .and(minPriceExpression)
                .and(maxPriceExpression)
                .and(minArea != null && maxArea != null ? property.area.between(minArea, maxArea) : property.area.isNotNull())
                .and(minFloor != null && maxFloor != null ? property.floor.between(minFloor, maxFloor) : property.floor.isNotNull())
                .and(sinkExpression)
                .and(airConditionerExpression)
                .and(shoeRackExpression)
                .and(washingMachineExpression)
                .and(refrigeratorExpression)
                .and(wardrobeExpression)
                .and(gasRangeExpression)
                .and(inductionExpression)
                .and(bedExpression)
                .and(deskExpression)
                .and(microwaveExpression)
                .and(bookshelfExpression)
                .and(depositExpression)
                .and(monthlyRentExpression);
    }

    public BooleanExpression getPredicate() {
        return predicate;
    }
}
