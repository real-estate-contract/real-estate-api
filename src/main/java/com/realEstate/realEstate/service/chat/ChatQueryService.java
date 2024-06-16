package com.realEstate.realEstate.service.chat;



import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.realEstate.realEstate.model.dto.chatting.ChatRoomResponseDto;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.model.entity.chat.Chat;
import com.realEstate.realEstate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.realEstate.realEstate.model.entity.QProperty.property;
import static com.realEstate.realEstate.model.entity.QUser.user;
import static com.realEstate.realEstate.model.entity.chat.QChat.chat;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatQueryService {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserRepository userRepository;


    public List<ChatRoomResponseDto> getChattingList(Long memberNo) {
        return jpaQueryFactory.select(Projections.constructor(ChatRoomResponseDto.class,
                        chat.chatNo,
                        chat.createMember,
                        chat.joinMember,
                        chat.saleNo,
                        chat.regDate,
                        ExpressionUtils.as(
                                JPAExpressions.select(user.nickName)
                                        .from(user)
                                        .where(user.id.eq(
                                                new CaseBuilder()
                                                        .when(chat.createMember.eq(memberNo)).then(chat.joinMember)
                                                        .otherwise(chat.createMember)
                                        ))
                                , "username"),
                        ExpressionUtils.as(
                                JPAExpressions.select(user.imageUrl)
                                        .from(user)
                                        .where(user.id.eq(
                                                new CaseBuilder()
                                                        .when(chat.createMember.eq(memberNo)).then(chat.joinMember)
                                                        .otherwise(chat.createMember)
                                        ))
                                , "profile")
                ))
                .from(chat)
                .join(property).on(property.propertyId.eq(chat.saleNo))
                .where(chat.createMember.eq(memberNo).or(chat.joinMember.eq(memberNo)))
                .fetch();

    }





    // 현재 메시지를 받는 사람을 조회하는 메서드
    public User getReceiverNumber(Integer chatNo, Integer senderNo) {
        Chat chatroom = jpaQueryFactory.select(chat)
                .from(chat)
                .where(chat.chatNo.eq(chatNo))
                .fetchOne();

        Long memberNo = chatroom.getCreateMember().equals(senderNo) ?
                chatroom.getJoinMember() : chatroom.getCreateMember();

        return userRepository.findById(memberNo)
                .orElseThrow(IllegalStateException::new);
    }
    private BooleanExpression saleNoEq(Long saleNo) {
        return Objects.nonNull(saleNo) ? chat.saleNo.eq(saleNo) : null;
    }

}