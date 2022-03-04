package com.strabl.sdk.domain.entity;

import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class User extends BaseEntity {

  private String userUuid;
  private String email;
  private String password;
  private String fullName;
  private String phone_number;
  private Boolean isEnabled;
  private UserStatus status;
  private Boolean isseller;
}
