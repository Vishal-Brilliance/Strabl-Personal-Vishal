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
public class Payment extends BaseEntity{

    @PrePersist
    private void onCreate() {
        setPayment_uuid(java.util.UUID.randomUUID());
    }
    private UUID payment_uuid;

    @ManyToOne
    @JoinColumn(name = "payment_customer_id", nullable=false)
    private User user;

    private String paymentName;

    private boolean status;




}
