// ChatService.java

package com.realEstate.realEstate.service.Chat;

import com.realEstate.realEstate.model.entity.Chat.ChatMessage;
import com.realEstate.realEstate.model.entity.Chat.ChatRoom;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.ChatMessageRepository;
import com.realEstate.realEstate.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatRoom createRoom(User buyer, User seller) {
        ChatRoom room = new ChatRoom();
        room.setBuyer(buyer);
        room.setSeller(seller);
        room.getUsers().add(buyer);
        room.getUsers().add(seller);
        return chatRoomRepository.save(room);
    }

    public void sendMessage(User sender, ChatRoom room, String content) {
        ChatMessage message = new ChatMessage();
        message.setSender(sender);
        message.setChatRoom(room);
        message.setContent(content);
        chatMessageRepository.save(message);

        messagingTemplate.convertAndSendToUser(
                sender.getName(),
                "/queue/messages",
                message
        );

        for (User user : room.getUsers()) {
            if (!user.getName().equals(sender.getName())) {
                messagingTemplate.convertAndSendToUser(
                        user.getName(),
                        "/queue/messages",
                        message
                );
            }
        }
    }
}
