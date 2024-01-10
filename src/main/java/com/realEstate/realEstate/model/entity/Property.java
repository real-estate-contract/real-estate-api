package com.realEstate.realEstate.model.entity;


import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Setter
@Table
public class Property extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @Enumerated(EnumType.STRING)
    private CType transactionType; // 매매, 전세, 월세 유형

    private int price; //매매가
    private int deposit;  // 보증금
    private int monthlyRent;  // 월세
    private int area; // 평형
    private int floor; // 층수
    private boolean parkingAvailable;
    private boolean hasElevator;
    private LocalDate moveInDate; // 입주 가능 날짜
    @Enumerated(EnumType.STRING)
    private Structure structure;

    @ManyToOne
    @JoinColumn(name = "adressId")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ToString.Exclude
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private PropertyOption option;

    @ToString.Exclude
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private Description description;


    public static Property of(CType transactionType, int price, int deposit, int monthlyRent, int area, int floor, boolean parkingAvailable, boolean hasElevator, LocalDate moveInDate, Structure structure, Address address, User user) {
        Property property = new Property();
        property.setTransactionType(transactionType);
        property.setPrice(price);
        property.setDeposit(deposit);
        property.setMonthlyRent(monthlyRent);
        property.setArea(area);
        property.setFloor(floor);
        property.setParkingAvailable(parkingAvailable);
        property.setHasElevator(hasElevator);
        property.setMoveInDate(moveInDate);
        property.setStructure(structure);
        property.setAddress(address);
        property.setUser(user);
        return property;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
