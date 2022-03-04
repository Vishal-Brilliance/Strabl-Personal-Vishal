package com.strabl.sdk.domain.redis.document;

import com.strabl.sdk.common.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@RedisHash(value = "StrablSession" , timeToLive = 24 * 60 * 60)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class StrablSession implements Serializable {

  private static final long serialVersionUID = 7004041433441767197L;

  @Id
  private String jwtToken;

  @Indexed
  private Integer userId;

  @TimeToLive
  private Long expiration;

  private UserResponseDTO profile;
}
