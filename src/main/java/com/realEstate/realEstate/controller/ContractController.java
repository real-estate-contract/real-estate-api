//package com.realEstate.realEstate.controller;
//
//import com.realEstate.realEstate.controller.request.contract.ContractCreateRequest;
//import com.realEstate.realEstate.controller.response.Response;
//import com.realEstate.realEstate.controller.response.contract.ContractResponse;

//import com.realEstate.realEstate.model.dto.ContractDto;

//import com.realEstate.realEstate.repository.UserRepository;
//import com.realEstate.realEstate.service.contract.ContractService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//@RequestMapping("/realEstate/contract")
//@RequiredArgsConstructor
//public class ContractController {
//
//    private final ContractService contractService;
//    private final UserRepository userRepository;
//
//    // 계약 생성
//    @PostMapping("/{propertyId}/{buyerId}")
//    public Response<Void> createContract(@PathVariable Long buyerId, @PathVariable Long propertyId,
//                                         @RequestBody ContractCreateRequest request, Authentication authentication) {
//        contractService.createContract( request.getTransactionType(), request.getContractAmount(), request.getContractDate(),
//                request.getTermUnit(), request.getTermLength(), request.getConditions(), buyerId, propertyId);
//
//        return Response.success();
//    }
//
//
//    // 계약 정보 조회
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    @GetMapping("/{contractId}")
//    public Response<ContractResponse> getContractById(@PathVariable Long contractId, Pageable pageable, Authentication authentication) {
//        ContractResponse contractResponse = ContractResponse.fromDto(contractService.findmyContract(contractId, pageable));
//        return Response.success(contractResponse);
//    }
//
//    // 계약 수정
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    @PostMapping("/{contractId}/edit")
//    public Response<ContractDto> modifyContract(@PathVariable Long contractId,
//                                         @RequestBody ContractDto request,
//                                         Pageable pageable, Authentication authentication){
//        ContractDto contractResponse = contractService.modifyContract(contractId, request, pageable);
//        return Response.success(contractResponse);
//    }
//}