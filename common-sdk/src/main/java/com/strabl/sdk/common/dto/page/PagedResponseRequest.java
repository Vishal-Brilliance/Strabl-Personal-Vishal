package com.strabl.sdk.common.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class PagedResponseRequest {

  @Positive
  private Integer pageNumber;

  @Positive
  private Integer pageSize;

  private boolean descending;

  private String keyword;

  private Long id;

  private List<String> sortProperties;

  public PagedResponseRequest() {
    this.pageNumber = 1;
    this.pageSize = 30;
    this.descending = true;
    this.sortProperties = new ArrayList<>();
    this.sortProperties.add("id");
  }

  public static PagedResponseRequest of(
      Integer pageNumber, Integer pageSize, Integer maxPageSize
  ) {
    return PagedResponseRequest.of(pageNumber, pageSize, maxPageSize, false, Collections.emptyList());
  }

  public static PagedResponseRequest of(
      Integer pageNumber, Integer pageSize, Integer maxPageSize, Boolean descending, List<String> sortProperties) {

    PagedResponseRequest pagedResponseRequest = new PagedResponseRequest();

    pagedResponseRequest.setPageNumber(pageNumber);
    pagedResponseRequest.setPageSize(Math.min(pageSize, maxPageSize));
    if (CollectionUtils.isEmpty(sortProperties)) {
      pagedResponseRequest.setSortProperties(List.of("id"));
    } else {
      pagedResponseRequest.setSortProperties(sortProperties);
    }

    pagedResponseRequest.setDescending(descending);

    return pagedResponseRequest;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = (pageNumber == null) ? 1 : pageNumber;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = (pageSize == null) ? 10 : pageSize;
  }

  public void setDescending(Boolean descending) {
    this.descending = descending != null && descending;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public void setSortProperties(List<String> sortProperties) {
    this.sortProperties = sortProperties;
  }
}
