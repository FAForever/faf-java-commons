package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Type("modVersionReview")
@EqualsAndHashCode(callSuper = true)
public class ModVersionReview extends Review {

    @Relationship("modVersion")
    private ModVersion modVersion;
}
