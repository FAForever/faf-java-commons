package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(of = "id")
@Type("nameRecord")
public class NameRecord implements ElideEntity {
  @Id
  private String id;
  private OffsetDateTime changeTime;
  @Relationship("player")
  private Player player;
  private String name;
}
