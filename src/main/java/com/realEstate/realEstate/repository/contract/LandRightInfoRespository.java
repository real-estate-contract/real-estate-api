package com.realEstate.realEstate.repository.contract;

import com.realEstate.realEstate.model.entity.LandPriceInfo;
import com.realEstate.realEstate.model.entity.LandRightInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandRightInfoRespository extends JpaRepository<LandRightInfo, Long> {
    Optional<LandRightInfo> findById(Long id);
}
