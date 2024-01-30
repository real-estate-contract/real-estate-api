package com.realEstate.realEstate.repository;

import com.realEstate.realEstate.model.entity.PropertyAmenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyAmenitiesRepository extends JpaRepository<PropertyAmenities, Long> {
}
