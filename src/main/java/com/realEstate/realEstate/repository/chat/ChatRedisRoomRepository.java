package com.realEstate.realEstate.repository.chat;


import com.realEstate.realEstate.model.entity.chat.ChatRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRedisRoomRepository extends CrudRepository<ChatRoom, String> {

    List<ChatRoom> findByChatroomNo(Integer chatRoomNo);

    Optional<ChatRoom> findByChatroomNoAndEmail(Integer chatRoomNo, String email);
}