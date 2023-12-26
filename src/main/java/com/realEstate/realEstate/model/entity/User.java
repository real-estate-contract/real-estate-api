package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Setter
@Table
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column
    private String email; // 명세서에서 추가된 부분

    @Column
    private Gender gender;

    @Column
    private int age;

    @Column
    private UserRole role;

    public static User of(String userName, String password, String email, Gender gender, int age, UserRole userRole) {
        User entity = new User();
        entity.setUserName(userName);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setGender(gender);
        entity.setAge(age);
        entity.setRole(userRole);
        return entity;
    }
}
