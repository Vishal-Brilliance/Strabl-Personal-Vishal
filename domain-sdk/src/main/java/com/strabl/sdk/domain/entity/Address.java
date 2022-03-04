package com.strabl.sdk.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@ToString(callSuper = true)
@NoArgsConstructor
public class Address extends BaseEntity {

  @PrePersist
  private void onCreate() {
    setUuid(java.util.UUID.randomUUID());
  }

  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private boolean status;
  @NotBlank(message = "city can not be blank")
  private String city;
  @NotBlank(message = "area can not be blank")
  private String area;
  @NotBlank(message = "building can not be blank")
  private String building;
  @NotBlank(message = "streetName can not be blank")
  private String streetName;
  @NotBlank(message = "flatNumber can not be blank")
  private String flatNumber;
}
