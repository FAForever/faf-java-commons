package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@Type("mapVersionReview")
@EqualsAndHashCode(callSuper = true)
public class MapVersionReview extends Review {

    @Relationship("mapVersion")
    MapVersion mapVersion;
}
