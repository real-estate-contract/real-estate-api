package com.realEstate.realEstate.model.entity.chat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "CHAT_IMAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatImage extends BaseImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_no")
    private Long pictureNo;

    @Column(name = "chat_no")
    private Integer chatNo;

    @Column(name = "image_url")
    private String imageUrl;

    public ChatImage(Integer chatNo, String imageUrl) {
        this.chatNo = chatNo;
        this.imageUrl = imageUrl;
    }


}