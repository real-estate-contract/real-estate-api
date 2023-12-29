package com.realEstate.realEstate.repository;


import com.realEstate.realEstate.model.entity.PropertyOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyOptionRepository extends JpaRepository<PropertyOption, Long > {
}
