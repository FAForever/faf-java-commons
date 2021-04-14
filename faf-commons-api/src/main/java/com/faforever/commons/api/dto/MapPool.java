package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("mapPool")
public class MapPool extends AbstractEntity {

  private String name;

  @Relationship("matchmakerQueueMapPool")
  @JsonIgnore
  private MatchmakerQueueMapPool matchmakerQueueMapPool;

  @Relationship("mapPoolAssignments")
  private List<MapPoolAssignment> mapPoolAssignments;

}
