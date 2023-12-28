package com.realEstate.realEstate.model.entity;


import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.HType;
import com.realEstate.realEstate.model.constant.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@ToString
@Setter
@Table
public class Property extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private HType type; // 매물형태

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int area; // 면적

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Property of(HType type, BigDecimal price, String address, int area, User user) {
        Property entity = new Property();
        entity.setType(type);
        entity.setPrice(price);
        entity.setAddress(address);
        entity.setArea(area);
        entity.setUser(user);
        return entity;
    }

}
