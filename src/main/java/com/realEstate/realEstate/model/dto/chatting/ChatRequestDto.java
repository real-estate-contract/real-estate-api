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
    private Long saleNo;
    @NotNull
    private Long createMember;

}
