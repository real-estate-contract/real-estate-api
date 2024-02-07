package com.realEstate.realEstate.repository.chat;

import com.realEstate.realEstate.model.dto.chatting.Chatting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MongoChatRepository extends MongoRepository<Chatting, String> {

    List<Chatting> findByChatRoomNo(Integer chatNo);

    Page<Chatting> findByChatRoomNoOrderBySendDateDesc(Integer chatRoomNo, Pageable pageable);
}