package com.realEstate.realEstate.model.dto;

// ChatRoomDTO.java

import com.realEstate.realEstate.model.entity.Chat.ChatRoom;
import com.realEstate.realEstate.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ChatRoomDTO {
    private Long roomId;
    private List<String> userNames;

    public ChatRoomDTO(ChatRoom room) {
        this.roomId = room.getRoomId();
        this.userNames = room.getUsers().stream().map(User::getName).collect(Collectors.toList());
    }

    // Getter methods...
}
