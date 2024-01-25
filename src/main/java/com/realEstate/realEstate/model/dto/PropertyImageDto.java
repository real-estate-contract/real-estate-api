package com.realEstate.realEstate.model.dto;

import com.realEstate.realEstate.model.entity.PropertyImage;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyImageDto {

    private List<MultipartFile> images;


}
