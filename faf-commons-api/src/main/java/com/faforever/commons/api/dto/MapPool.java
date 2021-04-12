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

    String name;

    @Relationship("matchmakerQueueMapPool")
    @JsonIgnore
    MatchmakerQueueMapPool matchmakerQueueMapPool;

    @Relationship("mapPoolAssignments")
    List<MapPoolAssignment> mapPoolAssignments;

}
