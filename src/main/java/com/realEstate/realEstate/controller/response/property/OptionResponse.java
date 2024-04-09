package com.realEstate.realEstate.controller.response.property;

import com.realEstate.realEstate.model.dto.OptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionResponse {

    Long optionId;
    boolean sink; //싱크대
    boolean airConditioner; // 에어컨
    boolean shoeRack; //신발장
    boolean washingMachine; // 세탁기
    boolean refrigerator; // 냉장고
    boolean wardrobe; // 옷장
    boolean gasRange; // 가스레인지
    boolean induction; // 인덕션
    boolean bed; // 침대
    boolean desk; // 책상
    boolean microwave; // 전자레인지
    boolean bookshelf; // 책장
    Long propertyId;

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
                dto.getPropertyId()
        );
    }
}
