package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Type("mapVersionReview")
@EqualsAndHashCode(callSuper = true)
public class MapVersionReview extends Review {

    @Relationship("mapVersion")
    MapVersion mapVersion;
}
