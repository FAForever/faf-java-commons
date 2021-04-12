package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Type("mapPool")
public class MapPool extends AbstractEntity {

    String name;

    @Relationship("matchmakerQueueMapPool")
    @JsonIgnore
    MatchmakerQueueMapPool matchmakerQueueMapPool;

    @Relationship("mapPoolAssignments")
    List<MapPoolAssignment> mapPoolAssignments;

}
