package com.realEstate.realEstate.model.entity;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.realEstate.realEstate.controller.response.property.PropertyResponse;
import com.realEstate.realEstate.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class PropertyAmenities extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subway;
    private String bus;
    private String mart;
    private String cafe;
    private String laundry;
    private String hospital;
    private String bank;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "propertyId")
    private Property property;

    public static PropertyAmenities of(String subway, String bus, String mart, String cafe, String laundry, String hospital, String bank, Property property) {
        PropertyAmenities entity = new PropertyAmenities();
        entity.setSubway(subway);
        entity.setBus(bus);
        entity.setMart(mart);
        entity.setCafe(cafe);
        entity.setLaundry(laundry);
        entity.setHospital(hospital);
        entity.setBank(bank);
        entity.setProperty(property);

        return entity;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
