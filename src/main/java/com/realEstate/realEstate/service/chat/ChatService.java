// ChatService.java

package com.realEstate.realEstate.service.chat;

import com.realEstate.realEstate.model.dto.ChatMessageDTO;
import com.realEstate.realEstate.model.dto.ChatRoomDTO;
import com.realEstate.realEstate.model.entity.chat.ChatMessage;
import com.realEstate.realEstate.model.entity.chat.ChatRoom;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.ChatMessageRepository;
import com.realEstate.realEstate.repository.ChatRoomRepository;
import com.realEstate.realEstate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public ChatRoom createRoom(User buyer, User seller) {
        ChatRoom room = new ChatRoom();
        room.setBuyer(buyer);
        room.setSeller(seller);
        room.getUsers().add(buyer);
        room.getUsers().add(seller);
        User s = userRepository.findById(room.getSeller().getUserId()).orElseThrow();
        User b = userRepository.findById(room.getBuyer().getUserId()).orElseThrow();
        s.getChatRoomsAsSeller().add(room);
        b.getChatRoomsAsBuyer().add(room);
        userRepository.save(s);
        userRepository.save(b);

        return chatRoomRepository.save(room);
    }

    public void sendMessage(User sender, ChatRoom room, String content) {
        ChatMessage message = new ChatMessage();
        message.setSender(sender);
        message.setChatRoom(room);
        message.setContent(content);
        chatMessageRepository.save(message);

        // Convert to DTO
        ChatMessageDTO messageDTO = new ChatMessageDTO(sender, content);
        ChatRoomDTO roomDTO = new ChatRoomDTO(room);

//
        messagingTemplate.convertAndSendToUser(
                sender.getName(),
                "/queue/messages",
                messageDTO
        );

        for (User user : room.getUsers()) {
            if (!user.getName().equals(sender.getName())) {
                messagingTemplate.convertAndSendToUser(
                        user.getName(),
                        "/queue/messages",
                        messageDTO
                );
            }
        }
        messagingTemplate.convertAndSendToUser(
                roomDTO.getUserNames().get(0), // Update with the correct field
                "/queue/messages",
                messageDTO
        );


    }
}
