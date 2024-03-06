package com.realEstate.realEstate.service.chat;

import com.realEstate.exception.ApplicationException;
import com.realEstate.exception.ErrorCode;
import com.realEstate.realEstate.model.entity.chat.ChatRoom;
import com.realEstate.realEstate.repository.chat.ChatRedisRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRedisRoomRepository chatRoomRepository;

    @Transactional
    public void connectChatRoom(Integer chatRoomNo, String email) {
        ChatRoom chatRoom = ChatRoom.builder()
                .email(email)
                .chatroomNo(chatRoomNo)
                .build();
        chatRoomRepository.save(chatRoom);
    }

    @Transactional
    public void disconnectChatRoom(Integer chatRoomNo, String email) {
        ChatRoom chatRoom = chatRoomRepository.findByChatroomNoAndEmail(chatRoomNo, email).orElseThrow(() -> {throw new ApplicationException(ErrorCode.Property_NOT_FOUND,"없음");});

        chatRoomRepository.delete(chatRoom);

    }

    public boolean isAllConnected(Integer chatRoomNo) {
        List<ChatRoom> connectedList = chatRoomRepository.findByChatroomNo(chatRoomNo);
        return connectedList.size() == 2;
    }

    public boolean isConnected(Integer chatRoomNo) {
        List<ChatRoom> connectedList = chatRoomRepository.findByChatroomNo(chatRoomNo);
        return connectedList.size() == 1;
    }

}
