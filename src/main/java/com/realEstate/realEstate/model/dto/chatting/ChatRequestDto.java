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
    private Integer saleNo;
    @NotNull
    private Long createMember;

}
