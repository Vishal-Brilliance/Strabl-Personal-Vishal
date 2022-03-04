package com.strabl.sdk.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(indexes = {
    @Index(columnList = "userId"),
    @Index(columnList = "sessionToken", unique = true),
    @Index(columnList = "expiresAt")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

  @Id
  private String refreshToken;

  @Column(nullable = false)
  private Integer userId;

  @Column(nullable = false)
  private String sessionToken;

  @Column(nullable = false)
  private Instant expiresAt;
}
