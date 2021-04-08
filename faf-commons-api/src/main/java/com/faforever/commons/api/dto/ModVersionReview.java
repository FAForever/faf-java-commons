package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@Type("modVersionReview")
public class ModVersionReview extends Review {

    @Relationship("modVersion")
    ModVersion modVersion;
}
