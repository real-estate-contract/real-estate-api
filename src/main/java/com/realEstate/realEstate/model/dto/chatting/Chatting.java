package com.realEstate.realEstate.model.dto.chatting;

import lombok.*;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collation = "chatting")
@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Chatting {

    @Id
    private String id;
    private Integer chatRoomNo;
    private Integer senderNo;
    private String senderName;
    private String contentType;
    private String content;
    private LocalDateTime sendDate;
    private long readCount;
}
