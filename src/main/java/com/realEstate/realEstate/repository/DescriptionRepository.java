package com.realEstate.realEstate.repository;

import com.realEstate.realEstate.model.entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
