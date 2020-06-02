package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("featuredMod")
public class FeaturedMod extends AbstractEntity {
    private String description;
    private String displayName;
    private int order;
    private String gitBranch;
    private String gitUrl;
    private String bireusUrl;
    private String technicalName;
    private boolean visible;
}
