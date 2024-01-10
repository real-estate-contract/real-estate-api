package com.realEstate.realEstate.repository;

import com.realEstate.realEstate.model.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
