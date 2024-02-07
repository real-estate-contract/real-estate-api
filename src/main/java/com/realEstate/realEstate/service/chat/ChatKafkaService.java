package com.realEstate.realEstate.service.chat;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.dto.chatting.*;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.model.entity.chat.Chat;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.repository.chat.ChatRepository;
import com.realEstate.realEstate.repository.chat.MongoChatRepository;
import com.realEstate.realEstate.util.ConstantUtil;
import com.realEstate.realEstate.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ChatRoomService chatRoomService;
    private final ChatQueryService chatQueryService;

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

    public ChattingHistoryResponseDto getChattingList(Integer chatRoomNo, UserDto user) {
        updateCountAllZero(chatRoomNo, user.getEmail());
        List<ChatResponseDto> chattingList = mongoChatRepository.findByChatRoomNo(chatRoomNo)
                .stream()
                .map(chat -> new ChatResponseDto(chat, user.getUserId()))
                .collect(Collectors.toList());

        return ChattingHistoryResponseDto.builder()
                .chatList(chattingList)
                .email(user.getEmail())
                .build();
    }


    public List<ChatRoomResponseDto> getChatList(UserDto userDto, Long saleNo) {
        List<ChatRoomResponseDto> chatRoomList = chatQueryService.getChattingList(userDto.getUserId(), saleNo);

        chatRoomList
                .forEach(chatRoomDto -> {
                    // 채팅방별로 읽지 않은 메시지 개수를 셋팅
                    long unReadCount = countUnReadMessages(chatRoomDto.getChatNo(), userDto.getUserId());
                    chatRoomDto.setUnReadCount(unReadCount);

                    // 채팅방별로 마지막 채팅내용과 시간을 셋팅
                    Page<Chatting> chatting =
                            mongoChatRepository.findByChatRoomNoOrderBySendDateDesc(chatRoomDto.getChatNo(), PageRequest.of(0, 1));
                    if (chatting.hasContent()) {
                        Chatting chat = chatting.getContent().get(0);
                        ChatRoomResponseDto.LatestMessage latestMessage = ChatRoomResponseDto.LatestMessage.builder()
                                .context(chat.getContent())
                                .sendAt(chat.getSendDate())
                                .build();
                        chatRoomDto.setLatestMessage(latestMessage);
                    }
                });

        return chatRoomList;
    }

    public void sendMessage(Message message, String accessToken) {
        User findUser = userRepository.findByEmail(jwtUtil.getUid(accessToken)).orElseThrow(()->{throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");
        });

        //채팅방에 모든 유저가 참여중인지 확인한다.
        boolean isConnectedAll = chatRoomService.isAllConnected(message.getChatNo());

        // 1:1채팅이므로 2명 접속시 readCount 0 , 한명 접속시 1
        Integer readCount = isConnectedAll ? 0 : 1;

        // message 객체에 보낸시간, 보낸사람 memberNo, 닉네임을 셋팅해준다.
        message.setSendTimeAndSender(LocalDateTime.now(), findUser.getUserId(), findUser.getNickName(), readCount);

        sender.send(ConstantUtil.KAFKA_TOPIC,message);


    }

    @Transactional
    public Message sendNotificationAndSaveMessage(Message message){
        User findUser = userRepository.findById(message.getSenderNo())
                .orElseThrow(() -> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음");});

        if(message.getSenderEmail().equals(findUser.getEmail())){
            Chatting chatting = message.convertEntity();
            Chatting savedChat = mongoChatRepository.save(chatting);
            message.setId(savedChat.getId());
        }
        return message;

    }

    public void updateMessage(String email, Integer chatRoomNo) {
        Message message = Message.builder()
                .contentType("notice")
                .chatNo(chatRoomNo)
                .content(email + " 님이 들어오셨습니다.")
                .build();

        sender.send(ConstantUtil.KAFKA_TOPIC, message);
    }

    //읽지 않은 메시지 채팅창 입장시 읽음 처리
    public void updateCountAllZero(Integer chatNo, String email) {
        User findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음");});

        Update update = new Update().set("readCount", 0);
        Query query = new Query(Criteria.where("chatRoomNo").is(chatNo)
                .and("senderNo").ne(findUser.getUserId()));

        mongoTemplate.updateMulti(query, update, Chatting.class);
    }

    long countUnReadMessages(Integer chatRoomNo, Long senderNo) {
        Query query = new Query(Criteria.where("chatRoomNo").is(chatRoomNo)
                .and("readCount").is(1)
                .and("senderNo").ne(senderNo));

        return mongoTemplate.count(query, Chatting.class);
    }

}
