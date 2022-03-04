package com.strabl.sdk.domain.criteria.page;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.ValidationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class StrablPageable {

  private StrablPageable() {}

  public static Pageable createPageWithSort(PagedResponseRequest pageRequest) {

    Sort sort = getSortOrder(pageRequest.getSortProperties(), pageRequest.isDescending());
    if (pageRequest.getPageNumber() <= 0) {
      throw new ValidationException(ResponseType.PAGE_SIZE_INVALID);
    }
    return PageRequest.of(pageRequest.getPageNumber() - 1, pageRequest.getPageSize(), sort);
  }

  public static Sort getSortOrder(List<String> properties, boolean isDescending) {

    if (CollectionUtils.isEmpty(properties)) {
      properties = new ArrayList<>();
      properties.add("id");
    }
    String[] sortProperties = new String[properties.size()];

    for (int i = 0; i < properties.size(); i++) {
      sortProperties[i] = properties.get(i);
    }

    return Sort.by(isDescending ? Sort.Direction.DESC : Sort.Direction.ASC, sortProperties);
  }
}
