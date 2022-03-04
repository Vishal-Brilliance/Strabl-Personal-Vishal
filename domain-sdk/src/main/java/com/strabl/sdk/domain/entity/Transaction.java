package com.strabl.sdk.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Transaction extends BaseEntity{

    @PrePersist
    private void onCreate() {
        setUuid(java.util.UUID.randomUUID());
    }
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
    private String invoiceId;
    private Boolean isTbyb;
    private Double initialAmount;
    private Double remainingAmount;
    private Double commission;



}
