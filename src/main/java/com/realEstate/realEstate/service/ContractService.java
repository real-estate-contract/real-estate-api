package com.realEstate.realEstate.service;

import com.realEstate.realEstate.model.dto.ContractDto;
import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public ContractDto getContractById(Integer contractId) {
        // ContractRepository를 사용하여 계약 정보를 가져와서 ContractDto로 변환
        return contractRepository.findById(contractId)
                .map(ContractDto::from)
                .orElse(null); // 예외 처리 로직 추가
    }

    public List<ContractDto> getContractsByPropertyId(Integer propertyId) {
        // ContractRepository를 사용하여 부동산 ID에 해당하는 계약 정보들을 가져와서 ContractDto로 변환
        return contractRepository.findByPropertyId(propertyId).stream()
                .map(ContractDto::from)
                .toList();
    }

    // TODO: 다른 비즈니스 로직에 맞게 메소드 추가

}
