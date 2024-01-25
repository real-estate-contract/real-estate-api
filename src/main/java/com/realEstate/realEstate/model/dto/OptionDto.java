package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.PropertyOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link PropertyOption}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OptionDto {
    Long optionId;
    boolean sink;
    boolean airConditioner;
    boolean shoeRack;
    boolean washingMachine;
    boolean refrigerator;
    boolean wardrobe;
    boolean gasRange;
    boolean induction;
    boolean bed;
    boolean desk;
    boolean microwave;
    boolean bookshelf;
    Long propertyId;

    public static OptionDto from(PropertyOption entity) {
        return new OptionDto(
                entity.getOptionId(),
                entity.isSink(),
                entity.isAirConditioner(),
                entity.isShoeRack(),
                entity.isWashingMachine(),
                entity.isRefrigerator(),
                entity.isWardrobe(),
                entity.isGasRange(),
                entity.isInduction(),
                entity.isBed(),
                entity.isDesk(),
                entity.isMicrowave(),
                entity.isBookshelf(),
                entity.getProperty().getPropertyId()
        );
    }
}