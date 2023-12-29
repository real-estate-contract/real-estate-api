package com.realEstate.realEstate.model.dto;


import com.realEstate.realEstate.model.constant.Gender;
import com.realEstate.realEstate.model.constant.UserRole;
import com.realEstate.realEstate.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto implements UserDetails {

    private Integer userId;
    private String name;
    private String password;
    private String email;
    private Gender gender;
    private int age;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserDto from(User entity) {
        return new UserDto(
                entity.getUserId(),
                entity.getName(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getGender(),
                entity.getAge(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
