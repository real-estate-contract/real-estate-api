package com.realEstate.realEstate.repository.contract;

import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.model.entity.SignatureImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignatureRepository extends JpaRepository<SignatureImage, Long> {
    Optional<SignatureImage> findById(Long id);
    List<SignatureImage> findByContractId(Long contractId);
}
