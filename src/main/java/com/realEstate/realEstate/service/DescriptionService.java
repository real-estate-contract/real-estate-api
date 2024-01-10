package com.realEstate.realEstate.service;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.DescriptionDto;
import com.realEstate.realEstate.model.entity.Description;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.repository.DescriptionRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionService {
    private final PropertyRepository propertyRepository;
    private final DescriptionRepository descriptionRepository;

    @Transactional
    public void registerDescription(String memo, boolean loanAvailable, boolean petFriendly, Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, String.format("%s is not founded", propertyId));
        });
        property.setDescription(Description.of(memo, loanAvailable, petFriendly,property));

        descriptionRepository.save(Description.of(memo, loanAvailable, petFriendly,property));
    }

    @Transactional
    public DescriptionDto modify(Long descriptionId ,String memo, boolean loanAvailable, boolean petFriendly) {
        Description description = descriptionRepository.findById(descriptionId).orElseThrow(() -> {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "없음");});

        description.setMemo(memo);
        description.setLoanAvailable(loanAvailable);
        description.setPetFriendly(petFriendly);

        return DescriptionDto.from(description);
    }
}
