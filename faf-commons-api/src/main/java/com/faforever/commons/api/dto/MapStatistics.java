package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id"})
@EqualsAndHashCode(of = "id")
@Type("mapStatistics")
public class MapStatistics implements ElideEntity {
  @Id
  private String id;
  private int downloads;
  private int draws;
  private int plays;

  @Relationship("map")
  private Map map;
}
