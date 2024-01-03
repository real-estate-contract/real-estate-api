package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import com.realEstate.realEstate.model.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Setter
@Table
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

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
        entity.setName(userName);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setGender(gender);
        entity.setAge(age);
        entity.setRole(userRole);
        return entity;
    }

    public static User toEntity(UserDto buyer) {
        User user1 = new User();
        user1.setId(user1.getId());
        return user1;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
