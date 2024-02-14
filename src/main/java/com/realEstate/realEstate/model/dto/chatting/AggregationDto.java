package com.realEstate.realEstate.model.dto.chatting;

import lombok.*;

import java.io.Serializable;
@Getter @ToString @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AggregationDto implements Serializable {

    private Long saleNo;
    private String isIncrease;
    private AggregationTarget target;
}