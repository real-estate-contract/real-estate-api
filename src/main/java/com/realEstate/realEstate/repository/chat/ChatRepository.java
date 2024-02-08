package com.realEstate.realEstate.repository.chat;

import com.realEstate.realEstate.model.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    // 내가 만든 채팅방또는 내가 참여중인 채팅방을 전부 찾아주는 메서드
    @Query("select c from Chat c where c.createMember = :memberNo or c.joinMember = :memberNo")
    List<Chat> findChattingRoom(@Param("memberNo") Integer memberNo);
}