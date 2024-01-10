package com.realEstate.realEstate;

import com.realEstate.realEstate.controller.ChatController;
import com.realEstate.realEstate.model.entity.Chat.ChatMessage;
import com.realEstate.realEstate.model.entity.Chat.ChatRoom;
import com.realEstate.realEstate.model.entity.User;
import com.realEstate.realEstate.service.Chat.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

@WebMvcTest(ChatController.class)
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatService chatService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    @Test
    @WithMockUser(username = "cih8400")
    public void testSendMessage() throws Exception {
        // 테스트할 데이터
        Long roomId = 1L;
        String content = "Hello, world!";

        // MockMvc를 사용하여 /realEstate/chat/{roomId}/send 엔드포인트에 POST 요청을 보냄
        ResultActions result = mockMvc.perform(post("/realEstate/chat/{roomId}/send", roomId)
                .content(content));

        // HTTP 응답이 200 OK인지 확인
        result.andExpect(status().isOk());

        // chatService.sendMessage 메서드가 호출되었는지 확인
        verify(chatService).sendMessage(any(User.class), any(ChatRoom.class), eq(content));

        // messagingTemplate.convertAndSendToUser 메서드가 호출되었는지 확인
        verify(messagingTemplate).convertAndSendToUser(eq("cih8400"), eq("/queue/messages"), any(ChatMessage.class));
    }

    // createRoom 및 chatRoom 메서드에 대한 테스트도 추가 가능
}
