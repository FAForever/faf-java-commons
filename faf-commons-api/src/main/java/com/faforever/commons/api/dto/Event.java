package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Type("event")
public class Event {
  @Id
  private String id;
  private String name;
  private String imageUrl;
  private Type type;

  public enum Type {
    NUMERIC, TIME
  }
}
