package com.realEstate.realEstate.controller;


import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.controller.request.ChatRequest;
import com.realEstate.realEstate.model.entity.Chat.ChatRoom;
import com.realEstate.realEstate.model.entity.Property;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.ChatRoomRepository;
import com.realEstate.realEstate.repository.PropertyRepository;
import com.realEstate.realEstate.repository.UserRepository;
import com.realEstate.realEstate.service.Chat.ChatService;
import com.realEstate.realEstate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/realEstate")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ChatRoomRepository chatRoomRepository;


    @MessageMapping("/chat/{roomId}/send")
    public void sendMessage(@DestinationVariable Long roomId, @Payload String content, Authentication authentication) {
        User sender = userRepository.findByName(authentication.getName()).orElseThrow(()->{throw new ApplicationException(ErrorCode.USER_NOT_FOUND,"없음");
        });
        ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        chatService.sendMessage(sender,room,content);
        System.out.println("Received message for room " + roomId + ": " + content);

    }

    @PostMapping("/chat/{userId}/create")
    public String createRoom(@PathVariable Long userId, Authentication authentication) {

        User user1 = userRepository.findByName(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        User user2 = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        ChatRoom room = chatService.createRoom(user1, user2);
        return "redirect:/chat/" + room.getRoomId();
    }

    @GetMapping("/chat/{roomId}")
    public String chatRoom(@PathVariable Long roomId, Model model){
        ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        model.addAttribute("room", room);
        return "chatRoom";
    }
}


