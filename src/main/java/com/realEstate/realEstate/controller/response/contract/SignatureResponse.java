package com.realEstate.realEstate.controller.response.contract;

import com.realEstate.realEstate.model.entity.SignatureImage;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class SignatureResponse {
    private final List<String> imageUrls;

    public static SignatureResponse of (List<SignatureImage> signatureImages){
        return new SignatureResponse(
                signatureImages.stream()
                        .map(SignatureImage::getImageUrl)
                        .collect(Collectors.toList())
        );
    }
}
