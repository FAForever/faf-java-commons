package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Type("mapVersionReview")
@EqualsAndHashCode(callSuper = true)
public class MapVersionReview extends Review {

    @Relationship("mapVersion")
    private MapVersion mapVersion;
}
