package com.realEstate.realEstate.service.contract;

import com.amazonaws.services.kms.model.NotFoundException;
import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.request.contract.ContractRequest;
import com.realEstate.realEstate.controller.response.Response;
import com.realEstate.realEstate.controller.response.UserJoinResponse;
import com.realEstate.realEstate.controller.response.contract.ContractResponse;
import com.realEstate.realEstate.model.UserInfo;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.TermUnit;
import com.realEstate.realEstate.model.dto.ContractDto;
import com.realEstate.realEstate.model.entity.Contract;

import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.contract.ContractRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;

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
     * @param principal
     */
    @Transactional
    public Response<ContractResponse> createContract(ContractRequest contractRequest, Long propertyId, Principal principal) {
        try {
            //user exit
            User user = userRepository.findById(UserInfo.of(principal.getName()).getPrimaryKey()).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("user is not founded")));
            //property exit
            Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", propertyId)));


            // DTO를 Contract 엔티티로 매핑
            Contract contract = modelMapper.map(contractRequest, Contract.class);

            // Contract 저장
            contract.setUser(user);
            contract.setProperty(property);
            Contract savedContract = contractRepository.save(contract);

            return Response.success(ContractResponse.of(savedContract));
        } catch (NotFoundException e) {
            throw new ApplicationException(ErrorCode.CONTRACT_NOT_FOUND, String.format("%s is not founded", user));
        } catch (Exception e) {
            log.error("계약 등록 요청 실패 = {}", e.getMessage());
            throw new RuntimeException("계약 등록 요청 실패.");
        }
    }

}