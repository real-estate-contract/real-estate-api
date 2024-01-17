package com.realEstate.realEstate.service;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.PropertyImageDto;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.PropertyImage;
import com.realEstate.realEstate.repository.PropertyImageRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyImageService {


    private final PropertyRepository propertyRepository;
    private final PropertyImageRepository propertyImageRepository;

    @Value("${image.save-path}")
    private String savePath;

    public void uploadImage(Long propertyID, PropertyImageDto propertyImageDto) {
        Property property = propertyRepository.findById(propertyID)
                .orElseThrow(() -> new ApplicationException(ErrorCode.Property_NOT_FOUND, "없음"));

        List<PropertyImage> propertyImages = propertyImageDto.getImages().stream()
                .map(image -> new PropertyImage(property, saveImage(image)))
                .collect(Collectors.toList());

        propertyImageRepository.saveAll(propertyImages);
    }

    private String saveImage(MultipartFile image) {
        // 이미지를 서버에 저장하고 저장된 경로를 반환
        // 이미지 파일의 고유한 이름을 생성하고 중복을 피하기 위해 파일명에 타임스탬프 또는 UUId등을 사용
        // 저장 경로, 파일명 생성 등은 프로젝트의 환경에 따라 다를 수 있습니다.

        //디렉터리에 저장하는 코드
        String fileName = "image_" + System.currentTimeMillis() + "." + getFileExtension(image.getOriginalFilename());


        try {
            Files.copy(image.getInputStream(), Paths.get(savePath).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(ErrorCode.IMAGE_SAVE_ERROR, "이미지 저장 실패");
        }

        return savePath + fileName;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")+1);
    }


}
