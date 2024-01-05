package com.realEstate.realEstate.repository;

import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    // contractId로 사용자의 계약을 페이지로 조회
    Optional<Contract> findById(Long id);

}