package com.realEstate.realEstate.repository;


import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>,
        QuerydslPredicateExecutor<Property> {
    Optional<Property> findByPropertyId(Long id);

    public Page<Property> findAllByUser(User user, Pageable pageable);

}
