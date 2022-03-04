package com.strabl.sdk.domain.redis.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash(value = "EmailVerificationToken", timeToLive = 24 * 60 * 60)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class EmailVerificationToken implements Serializable {

  private static final long serialVersionUID = -4109601464865099884L;

  @Id
  private String token;

  private Integer userId;
}
