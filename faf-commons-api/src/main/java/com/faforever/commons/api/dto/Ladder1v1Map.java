package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Deprecated
@Value
@EqualsAndHashCode(of = "id")
@Type("ladder1v1Map")
public class Ladder1v1Map implements ElideEntity {
    @Id
    String id;
    @Relationship("mapVersion")
    MapVersion mapVersion;
}
