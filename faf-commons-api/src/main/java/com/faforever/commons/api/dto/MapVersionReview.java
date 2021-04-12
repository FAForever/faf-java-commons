package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@Type("mapVersionReview")
@EqualsAndHashCode(callSuper = true)
public class MapVersionReview extends Review {

    @Relationship("mapVersion")
    MapVersion mapVersion;
}
