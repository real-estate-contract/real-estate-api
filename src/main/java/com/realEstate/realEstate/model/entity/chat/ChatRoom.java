package com.realEstate.realEstate.model.entity.chat;


import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RedisHash(value = "chatRoom")
public class ChatRoom {
    @Id
    private String id;

    @Indexed
    private Integer chatroomNo;

    @Indexed
    private String email;

    @Builder
    public ChatRoom(Integer chatroomNo, String email) {
        this.chatroomNo = chatroomNo;
        this.email = email;
    }


}
