package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(of = "id")
@Type("mapVersionStatistics")
public class MapVersionStatistics implements ElideEntity {
    @Id
    String id;
    int downloads;
    int draws;
    int plays;

    @Relationship("mapVersion")
    MapVersion mapVersion;
}
