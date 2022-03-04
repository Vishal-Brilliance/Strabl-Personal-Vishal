package com.strabl.sdk.domain.entity;

import com.strabl.sdk.domain.entity.enums.columns.ClassificationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Classification extends BaseEntity {

  @PrePersist
  protected void onCreate() {
    setUuid(java.util.UUID.randomUUID());
  }

  private UUID uuid;
  private ClassificationType classificationType;
  private Integer minimumDays;
  private Long rentPerDay;
  private Long rentPerWeek;
  private Long rentPerMonth;
  private Integer minimumMonths;
  private Long rentLongTerm;
  private Boolean isFeatured;
}
