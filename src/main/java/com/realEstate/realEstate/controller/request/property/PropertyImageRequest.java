package com.realEstate.realEstate.controller.request.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropertyImageRequest {

//    private Long id; // 파일 번호(FK)
    private String originalName;
    private String savedFilePath;
    private long size;

}
