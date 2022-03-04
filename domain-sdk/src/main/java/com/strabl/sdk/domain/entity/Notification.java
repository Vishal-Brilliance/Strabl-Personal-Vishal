package com.strabl.sdk.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class Notification extends BaseEntity {

  @Column(name = "title")
  private String title;

  @Column(name = "body")
  private String body;

  @Column(name = "name")
  private String name;

  @Column(name = "_from")
  private String from;

  @Column(name = "_to")
  private String to;

  @Column(name = "notification_type")
  private Integer notificationType;

  @Column(name = "time_to_live")
  private Long timeToLive;

  @Column(name = "collapse_key")
  private String collapseKey;

  @Column(name = "content_available")
  private Boolean contentAvailable;

  @Column(name = "json_data")
  private String jsonData;
}
