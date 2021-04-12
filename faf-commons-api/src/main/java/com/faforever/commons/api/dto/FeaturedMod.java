package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@EqualsAndHashCode(of = "id")
@Type("featuredMod")
public class FeaturedMod implements ElideEntity {
    @Id
    String id;
    String description;
    String displayName;
    int order;
    String gitBranch;
    String gitUrl;
    String bireusUrl;
    String technicalName;
    boolean visible;
}
