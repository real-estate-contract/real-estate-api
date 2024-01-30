package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LandRightInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landRightId;

    private String ldaQotaRate; //대지권비율

    @Builder
    public LandRightInfo(String ldaQotaRate) {
        this.ldaQotaRate = ldaQotaRate;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
