package com.strabl.sdk.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.UUID;
@Data
@Entity
public class Photos extends BaseEntity {

    @PrePersist
    private void onCreate() {
        setUuid(java.util.UUID.randomUUID());
    }
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private boolean is_approved;
    private boolean status;
}
