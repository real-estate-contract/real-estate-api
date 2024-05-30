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
    private Structure structure; // 매물 유형
    private int price; // 매물가격
    private int weeklyFee; // 주차임
    private boolean deposit; // 보증금 여부
    private int depositFee; // 보증금비용
    private boolean management; // 관리비 여부
    @Column(name = "management_fee")
    private int managementFee; // 관리비
    private boolean UsageFee; // 개별 사용료
    private boolean negotiationFee; // 가격 협의 가능
    private boolean loanFund; // 융자
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate; // 계약 시작 날짜
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate; // 계약 종료 날짜
    private int minimum; // 최소 계약 기간
    private int roomCount; // 룸개수
    private int bathroomCount; // 욕실개수
    private int area1; // 공급면적(평)
    private int area2; // 전용면적(평)
    private int floor; // 해당층
    private int wholeFloor; // 전체층
    private boolean parkingAvailable; // 세대당 주차 가능 여부
    private boolean washingmachine; // 세탁기
    private boolean airconditioner; // 에어컨
    private boolean refrigerator; // 냉장고

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
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<PropertyImage> propertyImageList;

    @ToString.Exclude
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Wish> wishes = new ArrayList<>();




    public static Property of(int weeklyFee, boolean deposit, int depositFee, int price, boolean management, int managementFee, int wholeFloor, int floor, boolean parkingAvailable,  LocalDate startDate,LocalDate endDate,int minimum, Structure structure,boolean usageFee, boolean negotiationFee, boolean loanFund, int roomCount, int bathroomCount, int area1, int area2,boolean washingmachine, boolean airconditioner, boolean refrigerator, Address address, User user) {
        Property property = new Property();
        property.setPrice(price);
        property.setWeeklyFee(weeklyFee);
        property.setDeposit(deposit);
        property.setDepositFee(depositFee);
        property.setStructure(structure);
        property.setManagement(management);
        property.setManagementFee(managementFee);
        property.setUsageFee(usageFee);
        property.setNegotiationFee(negotiationFee);
        property.setLoanFund(loanFund);
        property.setStartDate(startDate);
        property.setEndDate(endDate);
        property.setMinimum(minimum);
        property.setRoomCount(roomCount);
        property.setBathroomCount(bathroomCount);
        property.setArea1(area1);
        property.setArea2(area2);
        property.setWholeFloor(wholeFloor);
        property.setFloor(floor);
        property.setParkingAvailable(parkingAvailable);
        property.setWashingmachine(washingmachine);
        property.setAirconditioner(airconditioner);
        property.setRefrigerator(refrigerator);
        property.setAddress(address);
        property.setUser(user);
        return property;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
