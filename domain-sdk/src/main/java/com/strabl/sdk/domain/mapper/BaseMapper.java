package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.request.BaseRequest;
import com.strabl.sdk.common.util.JsonUtils;
import com.strabl.sdk.domain.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class BaseMapper {

  protected static <T extends BaseEntity, K extends BaseRequest> T toEntity(K req, Class<T> clazz) {
    T t = JsonUtils.convertTo(req, clazz);
    if (t instanceof BaseEntity) {
      t.setCreatedAt(Instant.now());
      t.setUpdatedAt(Instant.now());
    }
//    t.setStatus(1);
    return t;
  }

//  protected <T extends BaseResponse, K extends BaseEntity> T toDTO(K entity, Class<T> clazz) {
//    T t = JsonUtils.convertTo(entity, clazz);
//    if (t instanceof BaseResponse) {
//      t.setCreatedAtTimestamp(entity.getCreatedAt().toEpochMilli());
//      t.setLastUpdatedAtTimestamp(entity.getUpdatedAt().toEpochMilli());
//    }
//    return t;
//  }
}
