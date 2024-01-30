package com.realEstate.realEstate.repository.contract;

import com.realEstate.realEstate.model.entity.LandInfo;
import com.realEstate.realEstate.model.entity.LandPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandPriceInfoRespository extends JpaRepository<LandPriceInfo, Long> {
    Optional<LandPriceInfo> findById(Long id);
}
