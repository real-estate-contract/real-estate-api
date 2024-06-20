package com.realEstate.realEstate.controller;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.response.StatusResponseDto;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.dto.chatting.ChatRequestDto;
import com.realEstate.realEstate.model.dto.chatting.ChatRoomResponseDto;
import com.realEstate.realEstate.model.dto.chatting.ChattingHistoryResponseDto;
import com.realEstate.realEstate.model.dto.chatting.Message;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.service.chat.ChatKafkaService;
import com.realEstate.realEstate.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatKafkaController {

    private final ChatKafkaService chatService;
    private final ChatRoomService chatRoomService;
    private final UserRepository userRepository;

    @PostMapping("/chatroom")
    public ResponseEntity<StatusResponseDto> createChatRoom(@RequestBody @Valid final ChatRequestDto requestDto, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(StatusResponseDto.addStatus(400));
        }
        User user = userRepository.findByName(authentication.getName()).orElseThrow(()-> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음");
        });

        chatService.makeChatRoom(UserDto.from(user), requestDto);
        return ResponseEntity.ok(StatusResponseDto.addStatus(200));
    }



    //채팅 내역 조회
    @GetMapping("/chatRoom/{roomNo}")
    public ResponseEntity<ChattingHistoryResponseDto> chattingList(@PathVariable("roomNo") Integer roomNo, Authentication authentication){
        User user = userRepository.findByName(authentication.getName()).orElseThrow(()-> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음");
        });
        ChattingHistoryResponseDto chattingList = chatService.getChattingList(roomNo, UserDto.from(user));
        return ResponseEntity.ok(chattingList);
    }

    // 채팅방 리스트 조회
    // 채팅방 리스트 조회
    @GetMapping("/chatroom")
    public ResponseEntity<List<ChatRoomResponseDto>> chatRoomList(Authentication authentication) {
        User user = userRepository.findByName(authentication.getName()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다");
        });

        List<ChatRoomResponseDto> chatList = chatService.getChatList(UserDto.from(user));

        for (ChatRoomResponseDto chatRoom : chatList) {
            User user2 = userRepository.findById(chatRoom.getJoinMember()).orElseThrow(() -> {
                throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다");
            });
            User user3 = userRepository.findById(chatRoom.getCreateMember()).orElseThrow(() -> {
                throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다");
            });
            // chatRoom을 이용한 설정 작업 수행
            if (chatRoom.getCreateMember() == user.getId()){
                chatRoom.setSaleTitle(user2.getName());
            }
            else {
                chatRoom.setSaleTitle(user3.getName());
            }
            // 필요한 설정 작업 추가
        }
        return ResponseEntity.ok(chatList);
    }

    @MessageMapping("/message")
//    @SendTo("/subscribe/chat")
    public void sendMessage(@Valid Message message, @Header("Authorization") final String accessToken) {
        chatService.sendMessage(message, accessToken);
    }

    // 채팅방 접속 끊기
    @PostMapping("/chatroom/{chatroomNo}")
    public ResponseEntity<StatusResponseDto> disconnectChat(@PathVariable("chatroomNo") Integer chatroomNo,
                                                            Authentication authentication) {
        User user = userRepository.findByName(authentication.getName()).orElseThrow(()-> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음");
        });

        chatRoomService.disconnectChatRoom(chatroomNo, user.getEmail());
        return ResponseEntity.ok(StatusResponseDto.success());
    }

    // 메시지 전송 후 callback
    @PostMapping("/chatroom/notification")
    public ResponseEntity<Message> sendNotification(@Valid @RequestBody Message message) {
        Message savedMessage = chatService.sendNotificationAndSaveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }


}
