package com.faforever.commons.api.dto;


import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Type("message")
@EqualsAndHashCode(callSuper = true)
public class Message extends AbstractEntity {
  @Id
  private String id;
  private String key;
  private String language;
  private String region;
  private String value;
}
