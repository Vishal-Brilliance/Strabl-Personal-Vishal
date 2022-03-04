package com.strabl.sdk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Product extends BaseEntity {

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
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne
  @JoinColumn(name = "subcategory_id", nullable = false)
  private Category subCategory;

  @ManyToOne
  @JoinColumn(name = "classification_id")
  private Classification classification;

  @OneToOne
  @JoinColumn(name = "tbyb_id")
  private TBYB tbyb;

  @ManyToMany(mappedBy = "productList")
  @JsonIgnore
  private List<Cart> cartList = new ArrayList<>();

  private String slug;

  @Column(length = 200)
  @Size(min = 1, max = 200)
  private String title;

  @Column(length = 512)
  @Size(min = 1, max = 512)
  private String description;

  @Size(min = 1, max = 255)
  private String features;

  @Size(min = 1, max = 255)
  private String tags;

  @ManyToOne
  @JoinColumn(name = "currency_id", nullable = false)
  private Currency currency;

  @ManyToOne
  @JoinColumn(name = "seller_id" , nullable = false)
  private User seller;

  private Long finalPrice;
  private Boolean isActive;
  private Boolean isApproved;
  private Boolean isFeatured;
  private String brand;
  @Override
  public String toString() {
    return "Product{}";
  }
}
