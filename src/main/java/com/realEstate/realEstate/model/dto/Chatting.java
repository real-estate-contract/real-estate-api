package com.realEstate.realEstate.model.dto;

import lombok.*;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collection = "realEstate")
@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Chatting {

    @Id
    private String id;
    private Integer chatRoomNo;
    private Long senderNo;
    private String senderName;
    private String contentType;
    private String content;
    private LocalDateTime sendDate;
    private long readCount;
}
