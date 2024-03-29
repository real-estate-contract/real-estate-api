package com.realEstate.realEstate.model.dto.ChatGpt;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자가 protected로 설정
public class CompletionRequestDto {

    private String model;

    private String prompt;

    private float temperature;

    @Builder
    CompletionRequestDto(String model, String prompt, float temperature) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = temperature;
    }
}
