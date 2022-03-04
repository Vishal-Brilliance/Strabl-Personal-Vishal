package com.strabl.sdk.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class ProductReview extends BaseEntity {

  @PrePersist
  protected void onCreate() {
    setUuid(java.util.UUID.randomUUID());
  }

  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "reviewer_id")
  private User reviewer;

  @Min(1)
  @Max(5)
  private Short rating;

  @Column(length = 512)
  @Size(min = 1, max = 512)
  private String review;

  private Boolean isApproved;
}
