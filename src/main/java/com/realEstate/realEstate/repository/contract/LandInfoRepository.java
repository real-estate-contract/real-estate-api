package com.realEstate.realEstate.repository.contract;

import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.model.entity.LandInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandInfoRepository extends JpaRepository<LandInfo, Long> {
    Optional<LandInfo> findById(Long id);
}
