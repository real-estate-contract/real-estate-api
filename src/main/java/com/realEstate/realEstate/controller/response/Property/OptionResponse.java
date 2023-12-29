package com.realEstate.realEstate.controller.response.Property;

import com.realEstate.realEstate.model.dto.OptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionResponse {

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
    PropertyResponse property;

    public static OptionResponse from(OptionDto dto) {
        return new OptionResponse(
                dto.getOptionId(),
                dto.isSink(),
                dto.isAirConditioner(),
                dto.isShoeRack(),
                dto.isWashingMachine(),
                dto.isRefrigerator(),
                dto.isWardrobe(),
                dto.isGasRange(),
                dto.isInduction(),
                dto.isBed(),
                dto.isDesk(),
                dto.isMicrowave(),
                dto.isBookshelf(),
                PropertyResponse.fromDto(dto.getProperty())
        );
    }
}
