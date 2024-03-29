package com.realEstate.realEstate.model.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "transaction_type")
    private CType transactionType; // 매매, 전세, 월세 유형

    private int price; //매매가
    private int deposit;  // 보증금
    @Column(name = "monthly_rent")
    private int monthlyRent;  // 월세
    @Column(name = "management_fee")
    private int managementFee; // 관리비
    private boolean condominium; //공동주택 / 단독주택여부
    private int area; // 평형
    private int floor; // 층수
    @Column(name = "parking_available")
    private boolean parkingAvailable;
    @Column(name = "has_elevator")
    private boolean hasElevator;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "move_in_date")
    private LocalDate moveInDate; // 입주 가능 날짜
    @Enumerated(EnumType.STRING)
    private Structure structure;
    private String direction;

    @ToString.Exclude
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private PropertyCondition propertyCondition;


    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private PropertyOption option;

    @ToString.Exclude
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private PropertyAmenities amenities;

    @ToString.Exclude
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private Description description;

    @ToString.Exclude
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<PropertyImage> propertyImageList;

    @ToString.Exclude
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Wish> wishes = new ArrayList<>();




    public static Property of(CType transactionType, int price, int deposit, int monthlyRent, int managementFee, boolean condominium, int area, int floor, boolean parkingAvailable, boolean hasElevator, LocalDate moveInDate, Structure structure, String direction, Address address, User user) {
        Property property = new Property();
        property.setTransactionType(transactionType);
        property.setPrice(price);
        property.setDeposit(deposit);
        property.setMonthlyRent(monthlyRent);
        property.setManagementFee(managementFee);
        property.setCondominium(condominium);
        property.setArea(area);
        property.setFloor(floor);
        property.setParkingAvailable(parkingAvailable);
        property.setHasElevator(hasElevator);
        property.setMoveInDate(moveInDate);
        property.setStructure(structure);
        property.setDirection(direction);
        property.setAddress(address);
        property.setUser(user);
        return property;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
