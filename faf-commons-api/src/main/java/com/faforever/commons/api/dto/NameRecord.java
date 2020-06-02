package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Type("nameRecord")
@EqualsAndHashCode(callSuper = true)
public class NameRecord extends AbstractEntity {
    private OffsetDateTime changeTime;
    @Relationship("player")
    private Player player;
    private String name;
}
