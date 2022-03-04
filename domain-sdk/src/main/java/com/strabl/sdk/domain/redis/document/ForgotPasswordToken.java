package com.strabl.sdk.domain.redis.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash(value = "ForgotPasswordToken", timeToLive = 24 * 60 * 60)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ForgotPasswordToken implements Serializable {

  private static final long serialVersionUID = 1452953997727387867L;

  @Id private String id;

  private Integer userId;
}
