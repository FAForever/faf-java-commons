package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Type("matchmakerQueueMapPool")
public class MatchmakerQueueMapPool extends AbstractEntity {

    Double minRating;
    Double maxRating;

    @Relationship("matchmakerQueue")
    MatchmakerQueue matchmakerQueue;

    @Relationship("mapPool")
    @JsonIgnore
    MapPool mapPool;

}
