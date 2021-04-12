package com.faforever.commons.api.dto;


import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Type("message")
public class Message implements ElideEntity {
  @Id
  String id;
  String key;
  String language;
  String region;
  String value;
}
