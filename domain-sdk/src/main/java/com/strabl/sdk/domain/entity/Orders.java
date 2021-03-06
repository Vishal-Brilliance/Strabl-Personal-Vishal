package com.strabl.sdk.domain.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.strabl.sdk.domain.entity.enums.columns.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;


@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Orders extends BaseEntity {

	@PrePersist
	private void onCreate() {
		setUuid(java.util.UUID.randomUUID());
	}
	private UUID uuid;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "orders_cart", joinColumns = @JoinColumn(name = "orders_id"),
			inverseJoinColumns = @JoinColumn(name = "cart_id"))
	private List<Cart> cartList;

	@ManyToOne
	@JoinColumn(name = "currency_id", nullable=false)
	private Currency currency;

	@ManyToOne
	@JoinColumn(name = "payment_id", nullable=false)
	private Payment payement;

	private PaymentType paymentType;

	private ClassificationType type;

	private Integer quantity;

	private OrdersStatus status;

	private Instant startDate;

	private Instant endDate;

	private Double ipf;

	private boolean isipf;

	@Override
	public String toString() {
		return "Orders{}";
	}

}
