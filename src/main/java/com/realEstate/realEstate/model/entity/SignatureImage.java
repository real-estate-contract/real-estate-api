package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Setter
@RequiredArgsConstructor
@Table(name = "signature_image")
public class SignatureImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    private Contract contract;

    public SignatureImage(Contract contract, String imageUrl) {
        this.contract = contract;
        this.imageUrl = imageUrl;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
