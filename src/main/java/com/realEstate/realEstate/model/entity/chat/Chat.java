package com.realEstate.realEstate.model.entity.chat;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Table(name = "chat")
@DynamicInsert
@AllArgsConstructor @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_no")
    private Integer chatNo;

    @Column(name = "create_member")
    private Long createMember;

    @Column(name = "join_member")
    private Long joinMember;

    @Column(name = "sale_no")
    private Long saleNo;

    @Column(name = "reg_date")
    private LocalDateTime regDate;


}
