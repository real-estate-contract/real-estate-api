package com.realEstate.realEstate.controller;

import com.realEstate.realEstate.controller.request.contract.ContractRequest;
import com.realEstate.realEstate.controller.request.contract.SignatureRequest;
import com.realEstate.realEstate.controller.response.Response;

import com.realEstate.realEstate.controller.response.contract.ContractResponse;
import com.realEstate.realEstate.controller.response.contract.SignatureResponse;
import com.realEstate.realEstate.model.dto.ContractDto;

import com.realEstate.realEstate.model.dto.PropertyImageDto;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.service.contract.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Path;

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
     * @param contractRequest
     * @return
     */
    @PostMapping("/create/{propertyId}")
    public Response<ContractResponse> createContract(@PathVariable(value = "propertyId") long propertyId,
                                                     @RequestBody ContractRequest contractRequest, Authentication authentication) {
        return contractService.createContract( contractRequest, propertyId, authentication);
    }

    /**
     * 계약 리스트 조회
     * @param authentication
     * @return
     */
    @GetMapping("/list")
    public Response<List<ContractResponse>> getContractList(Authentication authentication) {
        return contractService.getContractList(authentication);
    }

    /**
     * 계약 한 개 조회
     * @param authentication
     * @return
     */
    @GetMapping("/item/{contractId}")
    public Response<ContractResponse> getContractItem(@PathVariable(value = "contractId") long contractId, Authentication authentication) {
        return contractService.getContractItem(authentication, contractId);
    }

    /**
     * 계약 한 개 삭제
     * @param contractId
     * @param authentication
     * @return
     */
    @DeleteMapping("/delete/{contractId}")
    public Response<String> deleteContractItem(@PathVariable(value = "contractId") long contractId, Authentication authentication) {
        return contractService.deleteContractItem(authentication, contractId);
    }

    /**
     * 서명 저장
     */
    @PostMapping("/signature/{contractId}")
    public Response<Void> uploadSignature(@PathVariable Long contractId, @RequestParam("images") List<MultipartFile> images) {

        SignatureRequest signatureDto = new SignatureRequest();
        signatureDto.setImages(images);
        contractService.uploadSignature(contractId, signatureDto);
        return Response.success();
    }

    /**
     * 서명 조회
     */
    @GetMapping("/signature/{contractId}")
    public Response<SignatureResponse> getSignature(@PathVariable Long contractId){

        return contractService.getSignature(contractId);
    }



}