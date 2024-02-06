package com.realEstate.realEstate.model.entity.chat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "chatRoom")
public class ChatRedisRoom {
    @Id
    private String id;

    @Indexed
    private Integer chatroomNo;

    @Indexed
    private String email;

    @Builder
    public ChatRedisRoom(Integer chatroomNo, String email) {
        this.chatroomNo = chatroomNo;
        this.email = email;
    }


}
