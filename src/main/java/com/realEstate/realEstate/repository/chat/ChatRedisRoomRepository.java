package com.realEstate.realEstate.repository.chat;


import com.realEstate.realEstate.model.entity.chat.ChatRedisRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRedisRoomRepository extends CrudRepository<ChatRedisRoom, String> {

    List<ChatRedisRoom> findByChatroomNo(Integer chatRoomNo);

    Optional<ChatRedisRoom> findByChatroomNoAndEmail(Integer chatRoomNo, String email);
}