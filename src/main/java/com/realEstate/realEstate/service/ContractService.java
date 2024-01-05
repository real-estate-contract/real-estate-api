package com.realEstate.realEstate.service;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Term;
import com.realEstate.realEstate.model.dto.ContractDto;
import com.realEstate.realEstate.model.dto.PropertyDto;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.ContractRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    // 새로운 계약을 생성
    @Transactional
    public void createContract(Integer id, CType type, BigDecimal contractAmount, Date contractDate,
                               Term termUnit, int termLength, String conditions, PropertyDto property, UserDto buyer) {

        contractRepository.save(Contract.of(id, type, contractAmount, contractDate, termUnit, termLength, conditions, property, buyer));
    }

    // 계약 조회
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



    // 기존 계약 업데이트
    @Transactional
    public ContractDto updateContract(CType type, BigDecimal contractAmount, Date contractDate, Term termUnit, int termLength,
                                      String conditions, PropertyDto property, UserDto buyer, String name, Integer contractId) {

        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new ApplicationException(ErrorCode.Property_NOT_FOUND, String.format("%s is not founded", contractId)));

//        // 해당 유저가 적은게 맞는지
//        if (contract.getBuyer() != buyer) {
//            throw new ApplicationException(ErrorCode.Invalid_Permission, String.format("%s has no permission", buyer));
//        }

        contract.setType(type);
        contract.setContractAmount(contractAmount);
        contract.setContractDate(contractDate);
        contract.setTermUnit(termUnit);
        contract.setTermLength(termLength);
        contract.setConditions(conditions);

        return ContractDto.from(contractRepository.saveAndFlush(contract));
    }

    // TODO: 다른 비즈니스 로직에 맞게 메소드 추가

}