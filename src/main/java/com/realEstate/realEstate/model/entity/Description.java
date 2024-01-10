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
public class Description {
    // 상세 설명 부분
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memo;

    @Column(nullable = false)
    private boolean loanAvailable; // 대출 가능 여부

    @Column(nullable = false)
    private boolean petFriendly; // 반려 동물 가능 여부

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "propertyId", unique = true)
    private Property property;

    public static Description of(String memo, boolean loanAvailable, boolean petFriendly, Property property) {
        Description description = new Description();
        description.setMemo(memo);
        description.setLoanAvailable(loanAvailable);
        description.setPetFriendly(petFriendly);
        description.setProperty(property);
        return description;
    }
}

