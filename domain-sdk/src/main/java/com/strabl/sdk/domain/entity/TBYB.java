package com.strabl.sdk.domain.entity;

import com.strabl.sdk.domain.entity.enums.columns.TrialDaysEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class TBYB extends BaseEntity {

  @PrePersist
  protected void onCreate() {
    setUuid(java.util.UUID.randomUUID());
  }

  private UUID uuid;

  private TrialDaysEnum trialDays;


}
