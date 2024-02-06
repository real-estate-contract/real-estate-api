package com.realEstate.realEstate.service.chat;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.dto.chatting.AggregationDto;
import com.realEstate.realEstate.model.dto.chatting.AggregationTarget;
import com.realEstate.realEstate.model.dto.chatting.ChatRequestDto;
import com.realEstate.realEstate.model.dto.chatting.Message;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.model.entity.chat.Chat;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.repository.chat.ChatRepository;
import com.realEstate.realEstate.repository.chat.MongoChatRepository;
import com.realEstate.realEstate.util.ConstantUtil;
import com.realEstate.realEstate.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatKafkaService {

    private final ChatRepository chatRepository;
    private final MongoChatRepository mongoChatRepository;
    private final MessageSender sender;
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtUtil;
    private final MongoTemplate mongoTemplate;
    private final AggregationSender aggregationSender;

    @Transactional
    public Chat makeChatRoom(UserDto userDto, ChatRequestDto requestDto) {
        Chat chat = Chat.builder()
                .saleNo(requestDto.getSaleNo())
                .createMember(requestDto.getCreateMember())
                .joinMember(userDto.getUserId())
                .regDate(LocalDateTime.now())
                .build();

        Chat savedChat = chatRepository.save(chat);

        //채팅방 카운트 증가
        AggregationDto aggregationDto = AggregationDto.builder()
                .isIncrease("true")
                .target(AggregationTarget.CHAT)
                .saleNo(requestDto.getSaleNo())
                .build();

        aggregationSender.send(ConstantUtil.KAFKA_AGGREGATION, aggregationDto);

        return savedChat;
    }

    public void sendMessage(Message message, String accessToken) {
        User findUser = userRepository.findByEmail(jwtUtil.getUid(accessToken)).orElseThrow(()->{throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");
        });
        boolean isConnectedAll = cha
    }

}
