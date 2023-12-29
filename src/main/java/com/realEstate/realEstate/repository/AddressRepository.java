package com.realEstate.realEstate.repository;

import com.realEstate.realEstate.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
