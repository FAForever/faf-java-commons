package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "event"}, callSuper = true)
@EqualsAndHashCode(of = "id")
@Type("playerEvent")
public class PlayerEvent {
  @Id
  private String id;
  private int currentCount;

  @Relationship("event")
  private Event event;
}
