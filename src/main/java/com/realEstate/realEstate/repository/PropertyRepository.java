package com.realEstate.realEstate.repository;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    public Page<Property> findAllByUser(User user, Pageable pageable);
}
