package com.realEstate.realEstate.service;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.DescriptionDto;
import com.realEstate.realEstate.model.entity.Description;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.repository.DescriptionRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.cacheRepository.PropertyCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionService {
    private final PropertyRepository propertyRepository;
    private final DescriptionRepository descriptionRepository;
    private final PropertyCacheRepository redisRepository;

    @Transactional
    public void registerDescription(String lineMemo, String memo, boolean loanAvailable, boolean petFriendly, Long propertyId) {
        Property property = loadPropertyByPropertyId(propertyId);

        Description description = Description.of(lineMemo, memo, loanAvailable, petFriendly,property);
        property.setDescription(description);

        descriptionRepository.save(description);
    }

    @Transactional
    public DescriptionDto modify(Long descriptionId ,String memo, boolean loanAvailable, boolean petFriendly) {
        Description description = descriptionRepository.findById(descriptionId).orElseThrow(() -> {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "없음");});

        description.setMemo(memo);
        description.setLoanAvailable(loanAvailable);
        description.setPetFriendly(petFriendly);

        return DescriptionDto.from(description);
    }

    public Property loadPropertyByPropertyId(Long propertyId) {
        return redisRepository.getProperty(propertyId).orElseGet(
                ()-> propertyRepository.findById(propertyId).orElseThrow(()->
                {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "매물 없음");
                })
        );

    }
}
