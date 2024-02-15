//package com.realEstate.realEstate.model.entity.chat;
//
//
//import com.realEstate.realEstate.model.BaseEntity;
//import com.realEstate.realEstate.model.entity.User;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//public class ChatMessage extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long messageId;
//
//    private String content;
//
//    @ManyToOne
//    @JoinColumn(name = "sender_id")
//    private User sender;
//
//    @ManyToOne
//    @JoinColumn(name = "room_id")
//    private ChatRoom chatRoom;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }
//
//}
//
