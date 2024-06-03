package com.realEstate.realEstate.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Setter
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(nullable = false, name = "street_address")
    private String streetAddress; // 도로명 주소

////    @Column(nullable = false)
//    private String city; // 상세 주소
//
////    @Column(nullable = false)
//    private boolean owner;

    public static Address of(String streetAddress) {
        Address address = new Address();
        address.setStreetAddress(streetAddress);
//        address.setCity(city);
//        address.setOwner(owner);
        return address;
    }
}
