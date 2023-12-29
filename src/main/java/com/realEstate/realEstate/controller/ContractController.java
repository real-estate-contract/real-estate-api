package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.model.dto.ContractDto;
import com.realEstate.realEstate.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    //계약 번호에 따른 계약정보
    @GetMapping("/{contractId}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable Integer contractId) {
        ContractDto contractDto = contractService.getContractById(contractId);
        return new ResponseEntity<>(contractDto, HttpStatus.OK);
    }


    // TODO: 다른 API 엔드포인트 및 비즈니스 로직에 맞게 메소드 추가

}
