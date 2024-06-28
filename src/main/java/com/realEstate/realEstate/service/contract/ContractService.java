package com.realEstate.realEstate.service.contract;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.request.contract.ContractRequest;
import com.realEstate.realEstate.controller.request.contract.SignatureRequest;
import com.realEstate.realEstate.controller.response.Response;
import com.realEstate.realEstate.controller.response.contract.ContractResponse;
import com.realEstate.realEstate.controller.response.contract.SignatureResponse;
import com.realEstate.realEstate.model.entity.*;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.repository.contract.ContractRepository;
import com.realEstate.realEstate.repository.contract.SignatureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.json.JsonParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.realEstate.realEstate.model.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractService {

    private final ContractRepository contractRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final SignatureRepository signatureRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


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

            //property exit
            Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s is not founded", propertyId)));

            // 본인 매물 거래 예외
            if (property.getUser().getId() == user.getId()) {
                throw new ApplicationException(ErrorCode.BAD_REQUEST, "본인 매물에는 본인이 계약할 수 없습니다.");
            }

            // 매물 상태 1로 변경 (계약 완료)
            property.setPropertyState(1);

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

    /**
     * 서명 저장
     * @param contractId
     * @param signatureDto
     */
    @Transactional
    public void uploadSignature(Long contractId, SignatureRequest signatureDto) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(()-> {throw new ApplicationException(ErrorCode.CONTRACT_NOT_FOUND, "계약서 없음");});
        List<SignatureImage> signatureImages = signatureDto.getImages().stream()
                .map(image -> new SignatureImage(contract, saveImage(image)))
                .collect(Collectors.toList());
        signatureRepository.saveAll(signatureImages);
    }
    private String saveImage(MultipartFile image) {

        String fileName = "signature_" + UUID.randomUUID() + "." + getFileExtension(image.getOriginalFilename());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        metadata.setContentType(image.getContentType());
        try {
            amazonS3.putObject(bucket, fileName, image.getInputStream(),metadata);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(ErrorCode.IMAGE_SAVE_ERROR, "이미지 저장 실패");
        }

        return amazonS3.getUrl(bucket,fileName).toString();
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")+1);
    }


    /**
     * 서명 조회
     * @param contractId
     * @return
     */

    public Response<SignatureResponse> getSignature(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(()-> {throw new ApplicationException(ErrorCode.CONTRACT_NOT_FOUND, "계약서 없음");});
        List<SignatureImage> signatureImages = signatureRepository.findByContractId(contractId);
        return Response.success(SignatureResponse.of(signatureImages));
    }
}