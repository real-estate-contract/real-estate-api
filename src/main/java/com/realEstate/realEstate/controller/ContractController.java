package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.controller.request.contract.ContractRequest;
import com.realEstate.realEstate.controller.response.Response;

import com.realEstate.realEstate.controller.response.contract.ContractResponse;
import com.realEstate.realEstate.model.dto.ContractDto;

import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.service.contract.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/realEstate/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;
    private final UserRepository userRepository;

    /**
     * 계약 생성
     * @param propertyId
     * @param request
     * @return
     */
    @PostMapping("/create/{propertyId}")
    public Response<ContractResponse> createContract(@PathVariable Long propertyId,
                                                     @RequestBody ContractRequest request, Principal principal) {
        return contractService.createContract( request, propertyId, principal);
    }

    /**
     * 계약 리스트 조회
     * @param principal
     * @return
     */
    @GetMapping("/list")
    public Response<List<ContractResponse>> getContractList(Principal principal) {
        return contractService.getContractList(principal);
    }

    /**
     * 계약 한 개 조회
     * @param principal
     * @return
     */
    @GetMapping("/item/{contractId}")
    public Response<ContractResponse> getContractItem(@PathVariable(value = "contractId") long contractId,Principal principal) {
        return contractService.getContractItem(principal, contractId);
    }



}