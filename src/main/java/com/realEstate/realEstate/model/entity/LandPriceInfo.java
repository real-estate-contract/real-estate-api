package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.CType;
import com.realEstate.realEstate.model.constant.Structure;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LandPriceInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landPriceId;

    private String pblntfPclnd; //개별공시지가

    public static LandPriceInfo of(String pblntfPclnd) {
        LandPriceInfo landPriceInfo = new LandPriceInfo();
        landPriceInfo.setPblntfPclnd(pblntfPclnd);
        return landPriceInfo;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
