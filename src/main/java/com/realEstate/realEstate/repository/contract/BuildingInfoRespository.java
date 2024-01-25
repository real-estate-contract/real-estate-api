package com.realEstate.realEstate.repository.contract;

import com.realEstate.realEstate.model.entity.BuildingInfo;
import com.realEstate.realEstate.model.entity.LandInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingInfoRespository extends JpaRepository<BuildingInfo, Long> {
    Optional<BuildingInfo> findById(Long id);
}
