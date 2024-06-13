package com.realEstate.realEstate.model.dto.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.realEstate.realEstate.model.entity.QProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PropertySearchCriteria {
    private final BooleanExpression predicate;

    public PropertySearchCriteria(Integer minPrice, Integer maxPrice, List<String> areaOptions,
                                  Integer minWeeklyFee, Integer maxWeeklyFee,
                                  Boolean refrigerator, Boolean washingMachine, Boolean airConditioner,
                                  Boolean parkingAvailable, Boolean includeManagementFee,
                                  LocalDate contractStartDate, LocalDate contractEndDate) {
        QProperty property = QProperty.property;

        BooleanExpression priceExpression;
        if (includeManagementFee != null && includeManagementFee) {
            // 관리비 포함일 경우
            priceExpression = minPrice == null && maxPrice == null ?
                    property.price.add(property.managementFee).isNotNull() :
                    (minPrice == null ?
                            property.price.add(property.managementFee).loe(maxPrice) :
                            (maxPrice == null ?
                                    property.price.add(property.managementFee).goe(minPrice) :
                                    property.price.add(property.managementFee).between(minPrice, maxPrice)));
        } else {
            // 관리비 미포함일 경우
            priceExpression = minPrice == null && maxPrice == null ?
                    property.price.isNotNull() :
                    (minPrice == null ?
                            property.price.loe(maxPrice) :
                            (maxPrice == null ?
                                    property.price.goe(minPrice) :
                                    property.price.between(minPrice, maxPrice)));
        }

        // 주차 임대료 조건
        BooleanExpression weeklyFeeExpression = property.weeklyFee.isNotNull();
        if (minWeeklyFee != null) {
            weeklyFeeExpression = weeklyFeeExpression.and(property.weeklyFee.goe(minWeeklyFee));
        }
        if (maxWeeklyFee != null) {
            weeklyFeeExpression = weeklyFeeExpression.and(property.weeklyFee.loe(maxWeeklyFee));
        }

        // 면적 조건
        BooleanExpression areaExpression = null;
        if (areaOptions != null && !areaOptions.isEmpty()) {
            List<BooleanExpression> areaExpressions = new ArrayList<>();
            for (String areaOption : areaOptions) {
                switch (areaOption) {
                    case "전체":
                        areaExpressions.add(property.area2.isNotNull());
                        break;
                    case "10평이하":
                        areaExpressions.add(property.area2.loe(10));
                        break;
                    case "10평대":
                        areaExpressions.add(property.area2.between(11, 20));
                        break;
                    case "20평대":
                        areaExpressions.add(property.area2.between(21, 30));
                        break;
                    case "30평대":
                        areaExpressions.add(property.area2.between(31, 40));
                        break;
                    case "40평대":
                        areaExpressions.add(property.area2.between(41, 50));
                        break;
                    case "50평대":
                        areaExpressions.add(property.area2.between(51, 60));
                        break;
                    case "60평이상":
                        areaExpressions.add(property.area2.goe(61));
                        break;
                    // 다른 면적 옵션들 추가
                }
            }
            areaExpression = areaExpressions.stream().reduce(BooleanExpression::and).orElse(null);
        }


        BooleanExpression refrigeratorExpression = refrigerator == null ? property.refrigerator.isNotNull() :
                property.refrigerator.eq(refrigerator);
        BooleanExpression washingMachineExpression;
        if (washingMachine == null) {
            washingMachineExpression = property.washingmachine.isNotNull();
        }
        else{
            washingMachineExpression = property.washingmachine.eq(washingMachine);
        }

        BooleanExpression airConditionerExpression = airConditioner == null ? property.airconditioner.isNotNull() :
                property.airconditioner.eq(airConditioner);
        BooleanExpression parkingAvailableExpression = parkingAvailable == null ? property.parkingAvailable.isNotNull() :
                property.parkingAvailable.eq(parkingAvailable);

        // 관리비 조건
        BooleanExpression managementFeeExpression = includeManagementFee == null ? property.managementFee.isNotNull() :
                includeManagementFee ? property.managementFee.gt(0) : property.managementFee.eq(0);

        // 계약 날짜 조건
        BooleanExpression contractStartDateExpression = contractStartDate == null ? property.startDate.isNotNull() :
                property.startDate.goe(contractStartDate);
        BooleanExpression contractEndDateExpression = contractEndDate == null ? property.endDate.isNotNull() :
                property.endDate.loe(contractEndDate);

        // 결합된 조건
        this.predicate = priceExpression
                .and(weeklyFeeExpression)
                .and(refrigeratorExpression)
                .and(washingMachineExpression)
                .and(airConditionerExpression)
                .and(parkingAvailableExpression)
                .and(managementFeeExpression)
                .and(contractStartDateExpression)
                .and(contractEndDateExpression)
                .and(areaExpression);
    }

    public BooleanExpression getPredicate() {
        return predicate;
    }
}
