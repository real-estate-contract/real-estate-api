package com.realEstate.realEstate.repository.contract;

import com.realEstate.realEstate.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    Optional<Contract> findById(Long id);

    List<Contract> findByUserId(Long userId);

//    List<Contract> findByUserId(Long userId);
}