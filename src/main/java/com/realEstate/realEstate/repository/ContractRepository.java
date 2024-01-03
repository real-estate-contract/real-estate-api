package com.realEstate.realEstate.repository;

import com.realEstate.realEstate.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    // 부동산 ID를 기반으로 계약 정보 조회
    List<Contract> findByPropertyId(Integer propertyId);

    // TODO: 다른 필요한 쿼리 메소드들 추가

}
