package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@Type("mapStatistics")
public class MapStatistics implements ElideEntity {
    @Id
    String id;
    int downloads;
    int draws;
    int plays;

    @Relationship("map")
    Map map;
}
