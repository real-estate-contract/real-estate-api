package com.realEstate.realEstate.repository;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.realEstate.realEstate.model.dto.querydsl.PropertySearchCriteria;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.model.entity.Wish;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>,
        QuerydslPredicateExecutor<Property> {
    Optional<Property> findByPropertyId(Long id);

    public Page<Property> findAllByUser(User user, Pageable pageable);

}
