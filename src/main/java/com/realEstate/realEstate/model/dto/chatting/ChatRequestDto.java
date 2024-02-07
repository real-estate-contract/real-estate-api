package com.realEstate.realEstate.model.dto.chatting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@AllArgsConstructor
public class ChatRequestDto {

    @NotNull
    private Long saleNo; // 매물 id
    @NotNull
    private Long createMember; // 매물 만든이

}
