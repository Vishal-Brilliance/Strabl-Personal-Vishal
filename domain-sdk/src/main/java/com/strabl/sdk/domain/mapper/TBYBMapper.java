package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.response.TBYBDTO;
import com.strabl.sdk.domain.entity.Address;
import com.strabl.sdk.domain.entity.Product;
import com.strabl.sdk.domain.entity.TBYB;
import com.strabl.sdk.domain.entity.enums.columns.TrialDaysEnum;

public class TBYBMapper {
  public static TBYBDTO toTBYBDTO(TBYB tbyb) {

    return TBYBDTO.builder()
        .uuid(tbyb.getUuid())
        .trialDays(tbyb.getTrialDays().getEntityCode())
        .build();
  }

  public static TBYB toTBYBEntityFrom(TBYBDTO tbybDto) {
    return TBYB.builder()
        .trialDays(TrialDaysEnum.from(tbybDto.getTrialDays()))
        .build();
  }
}
