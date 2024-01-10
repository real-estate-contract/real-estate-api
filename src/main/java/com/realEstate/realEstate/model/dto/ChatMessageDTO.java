package com.realEstate.realEstate.model.dto;


import com.realEstate.realEstate.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatMessageDTO {
    private String senderName;
    private String content;

    public ChatMessageDTO(User sender, String content) {
        this.senderName = sender.getName();
        this.content = content;
    }

    // Getter methods...
}