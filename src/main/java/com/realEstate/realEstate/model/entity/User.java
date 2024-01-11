package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.SocialType;
import com.realEstate.realEstate.model.constant.UserRole;
import com.realEstate.realEstate.model.entity.Chat.ChatRoom;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@Setter
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String name;

//    @Column(nullable = false) TODO : 소셜로그인 구현 후 비밀번호가 알아서 들어오는지 확인
    private String password;

    @Column
    private String email; // 명세서에서 추가된 부분

    @Column
    private Gender gender;

    @Column
    private int age;

    @Column
    private UserRole role;

    private String imageUrl; // 프로필 이미지

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    private String refreshToken; // 리프레시 토큰


    @OneToMany(mappedBy = "buyer")
    private List<ChatRoom> chatRoomsAsBuyer = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<ChatRoom> chatRoomsAsSeller = new ArrayList<>();

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

    public static User of(String userName, String email, UserRole userRole){
        User entity = new User();
        entity.setName(userName);
        entity.setEmail(email);
        entity.setRole(userRole);
        return entity;
    }

    //== 유저 필드 업데이트 ==//
    public void updateNickname(String updateNickname) {
        this.name = updateNickname;
    }


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = UserRole.USER;
    }
    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

}
