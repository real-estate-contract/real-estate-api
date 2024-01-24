// ChatService.java

package com.realEstate.realEstate.service.Chat;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.dto.ChatMessageDTO;
import com.realEstate.realEstate.model.dto.ChatRoomDTO;
import com.realEstate.realEstate.model.dto.UserDto;
import com.realEstate.realEstate.model.entity.Chat.ChatMessage;
import com.realEstate.realEstate.model.entity.Chat.ChatRoom;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.repository.ChatMessageRepository;
import com.realEstate.realEstate.repository.ChatRoomRepository;
import com.realEstate.realEstate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

        ChatMessageDTO messageDTO = new ChatMessageDTO(sender, content);
        ChatRoomDTO roomDTO = new ChatRoomDTO(room);

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
                roomDTO.getUserNames().get(0),
                "/queue/messages",
                messageDTO
        );


    }

    public List<ChatRoomDTO> getMyChatRooms(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "유저를 찾을 수 없음");
                });
        List<ChatRoom> chatRooms = chatRoomRepository.findByUsersContaining(user);

        // ChatRoom 엔티티를 ChatRoomDTO로 변환
        return chatRooms.stream()
                .map(ChatRoomDTO::new)
                .collect(Collectors.toList());
    }

    public void leaveChatRoom(Long userId, Long chatRoomId) {
        ChatRoom room= chatRoomRepository.findById(chatRoomId).orElseThrow(() ->
                {throw new ApplicationException(ErrorCode.Property_NOT_FOUND,"채팅방 없음");});
        User user = userRepository.findById(userId).orElseThrow(() ->
        {throw new ApplicationException(ErrorCode.USER_NOT_FOUND, "유저를 찾을 수 없음");
        });

        //채팅방에서 사용자 제거
        room.getUsers().remove(user);

        //사용자가 더 이상 채팅방에 속해있지 않으면 채팅방 삭제
        if (room.getUsers().isEmpty()) {
            chatRoomRepository.delete(room);
        } else {
            chatRoomRepository.save(room);
        }

        userRepository.save(user);
    }
}
