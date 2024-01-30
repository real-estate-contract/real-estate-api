package com.realEstate.realEstate.model.dto;


import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.PropertyAmenities;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmenitiesDto {

    Long id;
    String subway;
    String bus;
    String mart;
    String cafe;
    String laundry;
    String hospital;
    String bank;
    Long propertyId;

    public static AmenitiesDto from(PropertyAmenities entity) {
        return new AmenitiesDto(
                entity.getId(),
                entity.getSubway(),
                entity.getBus(),
                entity.getMart(),
                entity.getCafe(),
                entity.getLaundry(),
                entity.getHospital(),
                entity.getBank(),
                entity.getProperty().getPropertyId()
        );
    }

}
