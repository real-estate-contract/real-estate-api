package com.realEstate.realEstate.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.PropertyImageDto;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.PropertyImage;
import com.realEstate.realEstate.repository.PropertyImageRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.cacheRepository.PropertyCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyImageService {


    private final PropertyRepository propertyRepository;
    private final PropertyImageRepository propertyImageRepository;
    private final PropertyCacheRepository redisRepository;

    private final AmazonS3 amazonS3;


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public void uploadImage(Long propertyID, PropertyImageDto propertyImageDto) {
        Property property = loadPropertyByPropertyId(propertyID);

        List<PropertyImage> propertyImages = propertyImageDto.getImages().stream()
                .map(image -> new PropertyImage(property, saveImage(image)))
                .collect(Collectors.toList());

        propertyImageRepository.saveAll(propertyImages);
    }

    private String saveImage(MultipartFile image) {

        String fileName = "image_" + UUID.randomUUID() + "." + getFileExtension(image.getOriginalFilename());

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

    public Property loadPropertyByPropertyId(Long propertyId) {
        return redisRepository.getProperty(propertyId).orElseGet(
                ()-> propertyRepository.findById(propertyId).orElseThrow(()->
                {throw new ApplicationException(ErrorCode.Property_NOT_FOUND, "매물 없음");
                })
        );

    }


}
