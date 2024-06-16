package com.realEstate.realEstate.model.dto.chatting;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomResponseDto {

    private Integer chatNo;
    private Long createMember;
    private Long joinMember;
    private Long saleNo;
    private String saleTitle;
    private Long regDate;
    private Participant participant;
    private LatestMessage latestMessage;
    private Long unReadCount;

    public void setUnReadCount(Long unReadCount) {
        this.unReadCount = unReadCount;
    }

    public ChatRoomResponseDto(Integer chatNo, Long createMember, Long joinMember, Long saleNo, String saleTitle,
                               LocalDateTime regDate, String username, String profile) {
        this.chatNo = chatNo;
        this.createMember = createMember;
        this.joinMember = joinMember;
        this.saleNo = saleNo;
        this.saleTitle = saleTitle;
        this.regDate = regDate.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        this.participant = new Participant(username, profile);
    }

    public void setLatestMessage(LatestMessage latestMessage) {
        this.latestMessage = latestMessage;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Participant {
        private String username;
        private String profile;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LatestMessage {
        private String context;
        private Long sendAt;

        @Builder
        public LatestMessage(String context, LocalDateTime sendAt) {
            this.context = context;
            this.sendAt = sendAt.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        }
    }
}
