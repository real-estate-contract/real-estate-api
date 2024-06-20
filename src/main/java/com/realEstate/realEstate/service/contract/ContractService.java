package com.realEstate.realEstate.service.contract;

import com.amazonaws.services.kms.model.NotFoundException;
import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.request.contract.ContractRequest;
import com.realEstate.realEstate.controller.response.Response;
import com.realEstate.realEstate.controller.response.contract.ContractResponse;
import com.realEstate.realEstate.model.entity.Contract;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.repository.contract.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.json.JsonParseException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.realEstate.realEstate.model.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractService {

    private final ContractRepository contractRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private Contract save;


    /**
     * 계약 생성
     * 현재 로그인한 사용자가 생성하도록 구현
     * @param contractRequest
     * @param propertyId
     */
    @Transactional
    public Response<ContractResponse> createContract(ContractRequest contractRequest, Long propertyId, Authentication authentication) {
        try {
            //user exit
            User user = userRepository.findByName(authentication.getName()).orElseThrow(() -> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");});
//            log.info("principal Id ={}",UserInfo.of(principal.getName()).getPrimaryKey());
//            User user = userRepository.findById(UserInfo.of(principal.getName()).getPrimaryKey()).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("user is not founded")));

            //property exit
            Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", propertyId)));

            // DTO를 Contract 엔티티로 매핑
            Contract contract = modelMapper.map(contractRequest, Contract.class);
            log.error("{}",contract);
            contract.setUser(user);
            contract.setProperty(property);

            // Contract 저장
            Contract savedContract = contractRepository.save(contract);


            return Response.success(ContractResponse.of(savedContract));
        } catch (JsonParseException e) {
            log.error("Invalid JSON format: {}", e.getMessage());
            throw new RuntimeException("Invalid JSON format in request.");
        } catch (NotFoundException e) {
            throw new ApplicationException(ErrorCode.CONTRACT_NOT_FOUND, String.format("%s is not founded", user));
        } catch (Exception e) {
            log.error("계약 등록 요청 실패 = {}", e.getMessage());
            throw new RuntimeException("계약 등록 요청 실패.");
        }
    }

    /**
     * 계약 리스트 조회
     * @return
     */
    public Response<List<ContractResponse>> getContractList(Authentication authentication) {
        try{
            //user exit
            User user = userRepository.findByName(authentication.getName()).orElseThrow(() -> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");});
                List<Contract> contractList = contractRepository.findByUserId(user.getId());
                List<ContractResponse> contractResponseList = contractList.stream()
                        .map(ContractResponse::of)
                        .collect(Collectors.toList());
                return Response.success(contractResponseList);
        } catch (NotFoundException e) {
            throw new ApplicationException(ErrorCode.CONTRACT_NOT_FOUND, String.format("contract is not founded"));
        } catch (Exception e) {
            throw new RuntimeException("계약 리스트 조회에 실패했습니다.");
        }
    }


    /**
     * 계약 한 개 조회
     * @param contractId
     * @return
     */
    public Response<ContractResponse> getContractItem(Authentication authentication, long contractId) {
        try{
            //user exit
            User user = userRepository.findByName(authentication.getName()).orElseThrow(() -> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");});

            Optional<Contract> contractItem = contractRepository.findById(contractId);
            Contract contract = contractItem.orElseThrow(() -> new ApplicationException(ErrorCode.CONTRACT_NOT_FOUND, String.format("%s is not founded", contractId)));
            return Response.success(ContractResponse.of(contract));
        } catch (NotFoundException e) {
            throw new ApplicationException(ErrorCode.CONTRACT_NOT_FOUND, String.format("contract is not founded"));
        } catch (Exception e) {
            throw new RuntimeException("계약 조회에 실패했습니다.");
        }
    }

    /**
     * 계약 삭제
     * @param contractId
     * @return
     */
    public Response<String> deleteContractItem(Authentication authentication, long contractId) {
        try {
            //contract exit
            Optional<Contract> contractItem = Optional.ofNullable(contractRepository.findById(contractId))
                    .orElseThrow(() -> new ApplicationException(ErrorCode.CONTRACT_NOT_FOUND, String.format("%s is not founded", contractId)));
            contractRepository.delete(contractItem.get());
            return Response.success("계약 삭제 성공");
        } catch (Exception e) {
            throw new RuntimeException("계약 삭제에 실패했습니다.");
        }
    }
}