package com.realEstate.realEstate.service.chat;

import com.realEstate.realEstate.model.dto.chatting.Message;
import com.realEstate.realEstate.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class  MessageReceiver {
    private final SimpMessageSendingOperations template;

    @KafkaListener(topics = ConstantUtil.KAFKA_TOPIC, containerFactory = "stringMessageConcurrentKafkaListenerContainerFactory")
    public void receiveMessage(Message message) {
        log.info("전송 위치 = /subscribe/public/"+ message.getChatNo());
        log.info("채팅 방으로 메시지 전송 = {}", message);

        // 메시지객체 내부의 채팅방번호를 참조하여, 해당 채팅방 구독자에게 메시지를 발송한다.
        template.convertAndSend("/subscribe/public/" + message.getChatNo(), message);
    }
}
//test
