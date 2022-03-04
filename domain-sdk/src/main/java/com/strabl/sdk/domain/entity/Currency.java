package com.strabl.sdk.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class Currency extends BaseEntity {

    @PrePersist
    private void onCreate() {
        setUuid(java.util.UUID.randomUUID());
    }
    private UUID uuid;

    private String currencyIso;

    private String currencyName;

    private String currencySymbol;
}
