package com.realEstate.realEstate.service;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.PropertyImage;
import com.realEstate.realEstate.repository.PropertyImageRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyImageService {

    private PropertyRepository propertyRepository;
    private PropertyImageRepository propertyImageRepository;

    public void uploadImage(Long propertyID, List<MultipartFile> images) {
        Property property = propertyRepository.findById(propertyID).orElseThrow(() ->
        {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "없음");
        });
        // 이미지를 저장하고, propertyId에 매핑하여 DB에 저장
        for(MultipartFile image : images) {
            String imageUrl = saveImage(image);
            PropertyImage propertyImage  = new PropertyImage(property, imageUrl);
            propertyImageRepository.save(propertyImage);
        }
    }

    private String saveImage(MultipartFile image) {
        // 이미지를 서버에 저장하고 저장된 경로를 반환
        // 이미지 파일의 고유한 이름을 생성하고 중복을 피하기 위해 파일명에 타임스탬프 또는 UUId등을 사용
        // 저장 경로, 파일명 생성 등은 프로젝트의 환경에 따라 다를 수 있습니다.

        //디렉터리에 저장하는 코드
        String savePath = "/path/to/save/images";
        String fileName = "image_" + System.currentTimeMillis() + "./jpg";

        try {
            Files.copy(image.getInputStream(), Paths.get(savePath).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savePath + fileName;
    }


}
