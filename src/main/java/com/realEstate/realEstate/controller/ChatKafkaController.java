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
    @GetMapping("/chatroom")
    public ResponseEntity<List<ChatRoomResponseDto>> chatRoomList(@RequestParam(value = "saleNo", required = false) final Long saleNo, Authentication authentication) {
        User user = userRepository.findByName(authentication.getName()).orElseThrow(()-> {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "없음");
        });

        List<ChatRoomResponseDto> chatList = chatService.getChatList(UserDto.from(user), saleNo);
        return ResponseEntity.ok(chatList);
    }

    @MessageMapping("/message")
    public void sendMessage(@Valid Message message, @Header("Authorization") final String accessToken) {
        chatService.sendMessage(message, accessToken);
    }

    // 채팅방 접속 끊기
    @PostMapping("/chatroom/{chatroomNo}")
    public ResponseEntity<StatusResponseDto> disconnectChat(@PathVariable("chatroomNo") Integer chatroomNo,
                                                            @RequestParam("email") String email) {

        chatRoomService.disconnectChatRoom(chatroomNo, email);
        return ResponseEntity.ok(StatusResponseDto.success());
    }

    // 메시지 전송 후 callback
    @PostMapping("/chatroom/notification")
    public ResponseEntity<Message> sendNotification(@Valid @RequestBody Message message) {
        Message savedMessage = chatService.sendNotificationAndSaveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }


}
