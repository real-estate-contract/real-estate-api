//package com.realEstate.realEstate.model.dto.querydsl;
//
//
//import com.querydsl.core.types.ExpressionUtils;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.realEstate.realEstate.model.constant.CType;
//import com.realEstate.realEstate.model.constant.Structure;
//import com.realEstate.realEstate.model.entity.QProperty;
//
//import java.time.Year;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PropertySearchCriteria {
//    private final BooleanExpression predicate;
//
//    public PropertySearchCriteria(CType transactionType, Integer minPrice, Integer maxPrice,
//                                  List<String> areaOptions, List<String> floorOptions,
//                                  Structure structure, Boolean parkingAvailable, Boolean sink, Boolean airConditioner, Boolean shoeRack,
//                                  Boolean washingMachine, Boolean refrigerator, Boolean wardrobe, Boolean gasRange,
//                                  Boolean induction, Boolean bed, Boolean desk, Boolean microwave, Boolean bookshelf,
//                                  Integer minDeposit, Integer maxDeposit, Integer minMonthlyRent, Integer maxMonthlyRent, Integer yearOfCompletionOption,
//                                  Integer totalUnitOption, Boolean includeManagementFee) {
//        QProperty property = QProperty.property;
//
//        // 층수 조건을 추가합니다.
//        BooleanExpression floorExpression = null;
//        if (floorOptions != null && !floorOptions.isEmpty()) {
//            List<BooleanExpression> floorExpressions = new ArrayList<>();
//            for (String floorOption : floorOptions) {
//                switch (floorOption) {
//                    case "전체층":
//                        floorExpressions.add(property.floor.isNotNull());
//                        break;
//                    case "지상층":
//                        floorExpressions.add(property.floor.gt(0));
//                        break;
//                    case "반지하":
//                        floorExpressions.add(property.floor.lt(0));
//                        break;
//                    case "옥탑":
//                        floorExpressions.add(property.floor.eq(9999)); // 임의의 값, 실제 사용되는 값으로 변경해야 합니다.
//                        break;
//                    // 다른 층수 옵션들 추가
//                }
//            }
//            // 선택된 층수 옵션들을 OR로 결합합니다.
//            floorExpression = floorExpressions.stream().reduce(BooleanExpression::or).orElse(null);
//        }
//
//
//        // 면적 조건을 추가합니다.
//        BooleanExpression areaExpression = null;
//        if (areaOptions != null && !areaOptions.isEmpty()) {
//            List<BooleanExpression> areaExpressions = new ArrayList<>();
//            for (String areaOption : areaOptions) {
//                switch (areaOption) {
//                    case "전체":
//                        areaExpressions.add(property.area.isNotNull());
//                        break;
//                    case "10평이하":
//                        areaExpressions.add(property.area.loe(10));
//                        break;
//                    case "10평대":
//                        areaExpressions.add(property.area.between(11, 20));
//                        break;
//                    case "20평대":
//                        areaExpressions.add(property.area.between(21, 30));
//                        break;
//                    case "30평대":
//                        areaExpressions.add(property.area.between(31, 40));
//                        break;
//                    case "40평대":
//                        areaExpressions.add(property.area.between(41, 50));
//                        break;
//                    case "50평대":
//                        areaExpressions.add(property.area.between(51, 60));
//                        break;
//                    case "60평이상":
//                        areaExpressions.add(property.area.goe(61));
//                        break;
//                    // 다른 면적 옵션들 추가
//                }
//            }
//            // 선택된 면적 옵션들을 AND로 조합합니다.
//            areaExpression = areaExpressions.stream().reduce(BooleanExpression::and).orElse(null);
//        }
//
//
//        BooleanExpression transactionTypeExpression = transactionType == null ? property.transactionType.isNotNull() :
//                property.transactionType.eq(transactionType);
//
//        BooleanExpression structureExpression = structure == null ? property.structure.isNotNull() :
//                property.structure.eq(structure);
//
//        BooleanExpression sinkExpression = sink == null ? property.option.sink.isNotNull() :
//                property.option.sink.eq(sink);
//
//        BooleanExpression airConditionerExpression = airConditioner == null ? property.option.airConditioner.isNotNull() :
//                property.option.airConditioner.eq(airConditioner);
//
//        BooleanExpression shoeRackExpression = shoeRack == null ? property.option.shoeRack.isNotNull() :
//                property.option.shoeRack.eq(shoeRack);
//
//        BooleanExpression washingMachineExpression = washingMachine == null ? property.option.washingMachine.isNotNull() :
//                property.option.washingMachine.eq(washingMachine);
//
//        BooleanExpression refrigeratorExpression = refrigerator == null ? property.option.refrigerator.isNotNull() :
//                property.option.refrigerator.eq(refrigerator);
//
//        BooleanExpression wardrobeExpression = wardrobe == null ? property.option.wardrobe.isNotNull() :
//                property.option.wardrobe.eq(wardrobe);
//
//        BooleanExpression gasRangeExpression = gasRange == null ? property.option.gasRange.isNotNull() :
//                property.option.gasRange.eq(gasRange);
//
//        BooleanExpression inductionExpression = induction == null ? property.option.induction.isNotNull() :
//                property.option.induction.eq(induction);
//
//        BooleanExpression bedExpression = bed == null ? property.option.bed.isNotNull() :
//                property.option.bed.eq(bed);
//
//        BooleanExpression deskExpression = desk == null ? property.option.desk.isNotNull() :
//                property.option.desk.eq(desk);
//
//        BooleanExpression microwaveExpression = microwave == null ? property.option.microwave.isNotNull() :
//                property.option.microwave.eq(microwave);
//
//        BooleanExpression bookshelfExpression = bookshelf == null ? property.option.bookshelf.isNotNull() :
//                property.option.bookshelf.eq(bookshelf);
//
//        BooleanExpression minPriceExpression = transactionType == CType.SALE && minPrice != null ?
//                property.price.goe(minPrice) : null;
//
//        BooleanExpression maxPriceExpression = transactionType == CType.SALE && maxPrice != null ?
//                property.price.loe(maxPrice) : null;
//
//        BooleanExpression depositExpression = minDeposit == null && maxDeposit == null ?
//                property.deposit.isNotNull() : property.deposit.between(minDeposit, maxDeposit);
//
////        BooleanExpression monthlyRentExpression = minMonthlyRent == null && maxMonthlyRent == null ?
////                property.monthlyRent.isNotNull() : property.monthlyRent.between(minMonthlyRent, maxMonthlyRent);
//        BooleanExpression monthlyRentExpression = null;
//        if (minMonthlyRent != null && maxMonthlyRent != null) {
//            if (includeManagementFee) {
//                // 관리비를 포함한 월세를 계산하여 조건을 추가합니다.
//                monthlyRentExpression = property.monthlyRent.add(property.managementFee)
//                        .between(minMonthlyRent, maxMonthlyRent);
//            } else {
//                // 관리비를 포함하지 않은 월세를 기존과 동일하게 조건을 추가합니다.
//                monthlyRentExpression = property.monthlyRent.between(minMonthlyRent, maxMonthlyRent);
//            }
//        }
//
//        BooleanExpression parkingAvailableExpression = parkingAvailable == null ? property.parkingAvailable.isNotNull() :
//                property.parkingAvailable.eq(bookshelf);
//
//        BooleanExpression yearOfCompletionExpression = null;
//        if(yearOfCompletionOption != null){
//            int currentYear = Year.now().getValue();
//            int yearLimit = currentYear - (yearOfCompletionOption*5);
//
//            yearOfCompletionExpression = property.year.goe(yearLimit);
//
//        }
//        BooleanExpression totalUnitExpression = null;
//        if (totalUnitOption != null) {
//            switch (totalUnitOption) {
//                case 1: // 200세대 이상
//                    totalUnitExpression = property.generationCount.goe(200);
//                    break;
//                case 2: // 500세대 이상
//                    totalUnitExpression = property.generationCount.goe(500);
//                    break;
//                case 3: // 1000세대 이상
//                    totalUnitExpression = property.generationCount.goe(1000);
//                    break;
//                case 4: // 2000세대 이상
//                    totalUnitExpression = property.generationCount.goe(2000);
//                    break;
//                default: // 전체
//                    break;
//            }
//        }
//
//        this.predicate = transactionTypeExpression
//                .and(structureExpression)
//                .and(minPriceExpression)
//                .and(maxPriceExpression)
//                .and(sinkExpression)
//                .and(airConditionerExpression)
//                .and(shoeRackExpression)
//                .and(washingMachineExpression)
//                .and(refrigeratorExpression)
//                .and(wardrobeExpression)
//                .and(gasRangeExpression)
//                .and(inductionExpression)
//                .and(bedExpression)
//                .and(deskExpression)
//                .and(microwaveExpression)
//                .and(bookshelfExpression)
//                .and(depositExpression)
//                .and(monthlyRentExpression)
//                .and(parkingAvailableExpression)
//                .and(yearOfCompletionExpression)
//                .and(totalUnitExpression)
//                .and(areaExpression)
//                .and(floorExpression);
//    }
//
//    public BooleanExpression getPredicate() {
//        return predicate;
//    }
//}
