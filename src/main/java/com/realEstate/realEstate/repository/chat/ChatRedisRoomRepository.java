package com.realEstate.realEstate.repository.chat;


import com.realEstate.realEstate.model.entity.chat.ChatRedisRoom;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ChatRedisRoomRepository extends CrudRepository<ChatRedisRoom, String> {

    List<ChatRedisRoom> findByChatroomNo(Integer chatRoomNo);

    Optional<ChatRedisRoom> findByChatroomNoAndEmail(Integer chatRoomNo, String email);
}