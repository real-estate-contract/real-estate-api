package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.realEstate.realEstate.model.entity.Description}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DescriptionDto{
    Long id;
    String memo;
    boolean loanAvailable;
    boolean petFriendly;
    Long propertyId;

    public static DescriptionDto from(Description entity) {
        return new DescriptionDto(
                entity.getId(),
                entity.getMemo(),
                entity.isLoanAvailable(),
                entity.isPetFriendly(),
                entity.getProperty().getPropertyId()
        );
    }
}