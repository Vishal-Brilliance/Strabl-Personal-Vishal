package com.strabl.sdk.domain.entity;

import java.util.UUID;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(schema="strabl")
public class Order extends BaseEntity {

	@PrePersist
	private void onCreate() {
		setUuid(java.util.UUID.randomUUID());
	}
	private UUID uuid;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable=false)
	private User customer;
	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable=false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "currency_id", nullable=false)
	private Currency currency;
	
}
