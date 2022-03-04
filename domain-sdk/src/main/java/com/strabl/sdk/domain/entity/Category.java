package com.strabl.sdk.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@Where(clause = "is_deleted is null or is_deleted = false")
@SuperBuilder
@NoArgsConstructor
public class Category extends BaseEntity {

  private String name;
  private String description;
  private Integer orderIndex;
  private String image;
  private String icon;
  private Boolean isPublished;
  private Boolean isHome;
  private Boolean isRandomStuff;
  private Boolean isTbyb;
  private Boolean isRental;
  private String categoryUrl;
  private String url;

  @ManyToOne
  @JoinColumn(name = "created_by")
  private User createdBy;

  @ManyToOne
  @JoinColumn(name = "updated_by")
  private User updatedBy;
}
