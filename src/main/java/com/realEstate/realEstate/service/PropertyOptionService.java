package com.realEstate.realEstate.service;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.OptionDto;
import com.realEstate.realEstate.model.entity.PropertyOption;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.repository.PropertyOptionRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.cacheRepository.PropertyCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PropertyOptionService {

    private final PropertyOptionRepository optionRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyCacheRepository redisRepository;


    @Transactional
    public void registerOption(boolean sink, boolean airConditioner, boolean shoeRack, boolean washingMachine, boolean refrigerator, boolean wardrobe, boolean gasRange, boolean induction, boolean bed, boolean desk, boolean microwave, boolean bookshelf, Long propertyId){
        Property property = loadPropertyByPropertyId(propertyId);
        PropertyOption option = PropertyOption.of(sink, airConditioner, shoeRack, washingMachine, refrigerator, wardrobe, gasRange, induction,  bed, desk, microwave, bookshelf, property);

        property.setOption(option);

        optionRepository.save(option);
    }

    @Transactional
    public OptionDto modify(Long optionId, boolean sink, boolean airConditioner, boolean shoeRack, boolean washingMachine, boolean refrigerator, boolean wardrobe, boolean gasRange, boolean induction, boolean bed, boolean desk, boolean microwave, boolean bookshelf) {
        PropertyOption option = optionRepository.findById(optionId).orElseThrow(()->{throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "없음");
        });

        option.setSink(sink);
        option.setAirConditioner(airConditioner);
        option.setShoeRack(shoeRack);
        option.setWashingMachine(washingMachine);
        option.setRefrigerator(refrigerator);
        option.setWardrobe(wardrobe);
        option.setGasRange(gasRange);
        option.setInduction(induction);
        option.setBed(bed);
        option.setDesk(desk);
        option.setMicrowave(microwave);
        option.setBookshelf(bookshelf);

        return OptionDto.from(option);
    }

    public Property loadPropertyByPropertyId(Long propertyId) {
        return propertyRepository.findById(propertyId).orElseThrow(()->
                {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "매물 없음");}
        );

    }
}
