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
@Type("map")
@EqualsAndHashCode(callSuper = true)
public class Map extends AbstractEntity {
    String battleType;
    String displayName;
    String mapType;

    @Relationship("author")
    Player author;

    @Relationship("statistics")
    MapStatistics statistics;

    @Relationship("latestVersion")
    @JsonIgnore
    MapVersion latestVersion;

    @Relationship("versions")
    @JsonIgnore
    List<MapVersion> versions;

    @Relationship("reviewsSummary")
    MapReviewsSummary mapReviewsSummary;
}
