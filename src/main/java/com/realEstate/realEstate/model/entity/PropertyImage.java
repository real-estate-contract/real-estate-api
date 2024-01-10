package com.realEstate.realEstate.model.entity;


import com.realEstate.realEstate.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@ToString
@Setter
@Table
public class PropertyImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    private Property property;


    public PropertyImage(Property property, String imageUrl) {
        this.property = property;
        this.imageUrl = imageUrl;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
