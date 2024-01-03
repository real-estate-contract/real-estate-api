package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.controller.request.contract.ContractCreateRequest;
import com.realEstate.realEstate.controller.response.Response;
import com.realEstate.realEstate.controller.response.contract.ContractResponse;
import com.realEstate.realEstate.model.dto.ContractDto;
import com.realEstate.realEstate.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    // 계약 정보 조회
    @GetMapping("/{contractId}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable Integer contractId) {
        ContractDto contractDto = contractService.getContractById(contractId);
        return new ResponseEntity<>(contractDto, HttpStatus.OK);
    }

    // 계약 생성
    @PostMapping
    public Response<Void> createContract(@RequestBody ContractCreateRequest request, Authentication authentication) {
        contractService.createContract(request.getId(), request.getType(), request.getContractAmount(), request.getContractDate(),
                request.getTermUnit(), request.getTermLength(), request.getConditions(), request.getProperty(), request.getBuyer());

        return Response.success();
    }

    // 계약 업데이트
    @PatchMapping("/{contractId}")
    public Response<ContractResponse> updateContract(@PathVariable Integer contractId, @RequestBody ContractCreateRequest request, Authentication authentication) {
        ContractDto contractDto = contractService.updateContract(request.getType(), request.getContractAmount(), request.getContractDate(), request.getTermUnit(), request.getTermLength(), request.getConditions(), request.getProperty(), request.getBuyer(), authentication.getName(), contractId);

        return Response.success(ContractResponse.fromDto(contractDto));
    }



    // TODO: 다른 API 엔드포인트 및 비즈니스 로직에 맞게 메소드 추가

}
