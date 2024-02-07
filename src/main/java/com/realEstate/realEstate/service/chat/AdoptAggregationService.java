//package com.realEstate.realEstate.service.chat;
//
//
//
//import com.realEstate.realEstate.model.dto.chatting.AggregationDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class AdoptAggregationService {
//
//    private final AdoptAggregationRepository aggregationRepository;
//
//
//    @Transactional
//    public void aggregation(AggregationDto aggregationDto) {
//        switch (aggregationDto.getTarget()) {
//            case ADOPT:
//                aggregationBookmarkCount(aggregationDto.getSaleNo(), aggregationDto.getIsIncrease());
//                break;
//            case CHAT:
//                aggregationChatCount(aggregationDto.getSaleNo(), aggregationDto.getIsIncrease());
//                break;
//        }
//    }
//
//    @Transactional
//    public void aggregationChatCount(Long saleNo, String isIncrease) {
//        if (isIncrease.equals("true")) {
//            aggregationRepository.increaseChatCount(saleNo);
//            return;
//        }
//
//        aggregationRepository.decreaseChatCount(saleNo);
//    }
//
//}