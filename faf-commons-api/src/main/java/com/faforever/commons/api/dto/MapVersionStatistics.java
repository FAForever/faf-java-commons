package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id"}, callSuper = true)
@EqualsAndHashCode(of = "id")
@Type("mapVersionStatistics")
public class MapVersionStatistics implements ElideEntity {
  @Id
  private String id;
  private int downloads;
  private int draws;
  private int plays;

  @Relationship("mapVersion")
  private MapVersion mapVersion;
}
