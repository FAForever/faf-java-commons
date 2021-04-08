package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@Type("nameRecord")
public class NameRecord implements ElideEntity {
    @Id
    String id;
    OffsetDateTime changeTime;
    @Relationship("player")
    Player player;
    String name;
}
