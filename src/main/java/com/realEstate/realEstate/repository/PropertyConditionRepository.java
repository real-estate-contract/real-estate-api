package com.realEstate.realEstate.repository;

import com.realEstate.realEstate.model.entity.PropertyCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyConditionRepository extends JpaRepository<PropertyCondition, Long> {
}
