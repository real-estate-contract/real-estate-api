package com.realEstate.realEstate.service;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.TermUnit;
import com.realEstate.realEstate.model.dto.ContractDto;
import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.ContractRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;


    // 계약 create
    @Transactional
    public void createContract(CType transactionType, BigDecimal contractAmount, Date contractDate,
                               TermUnit termUnit, int termLength, String conditions, Long propertyId, Long buyerId) {

        //user exit
        User buyer = userRepository.findById(buyerId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", buyerId)));
        //property exit
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", propertyId)));

        contractRepository.save(Contract.of(transactionType, contractAmount, contractDate, termUnit, termLength, conditions, property, buyer));
    }

    // ReadAll(페이징 처리)
    public Page<ContractDto> contractList(Pageable pageable){
        return contractRepository.findAll(pageable).map(ContractDto::from);
    }

    // 내 계약 조회
    public ContractDto findmyContract(Long contractId, Pageable pageable) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, "Contract not found"));

        return ContractDto.from(contract);
    }

    // 계약 modify
    @Transactional
    public ContractDto modifyContract(CType type, BigDecimal contractAmount, Date contractDate, TermUnit termUnit, int termLength,
                                      String conditions, Long buyerId, Long contractId) {

        User buyer = userRepository.findById(buyerId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", buyerId)));
        // contract exit
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new ApplicationException(ErrorCode.Property_NOT_FOUND, String.format("%s is not founded", contractId)));

        // 해당 유저가 적은게 맞는지
        if (contract.getBuyer() != buyer) {
            throw new ApplicationException(ErrorCode.Invalid_Permission, String.format("%s has no permission", buyer));
        }

        contract.setType(type);
        contract.setContractAmount(contractAmount);
        contract.setContractDate(contractDate);
        contract.setTermUnit(termUnit);
        contract.setTermLength(termLength);
        contract.setConditions(conditions);

        return ContractDto.from(contractRepository.saveAndFlush(contract));
    }



}