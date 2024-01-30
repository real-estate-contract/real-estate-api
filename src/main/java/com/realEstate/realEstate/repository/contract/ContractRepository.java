package com.realEstate.realEstate.repository.contract;

import com.realEstate.realEstate.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    // contractId로 사용자의 계약을 페이지로 조회
//    public Page<Contract> findById(Long id, Pageable pageable);
    Optional<Contract> findById(Long id);

}