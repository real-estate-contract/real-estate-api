package com.realEstate.realEstate.repository;

import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    Page<Wish> findByUser(User user, Pageable pageable);

    Optional<Wish> findByUserAndProperty(User user, Property property);
}
