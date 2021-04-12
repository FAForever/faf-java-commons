package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Type("clanMembership")
public class ClanMembership extends AbstractEntity {
    @Relationship("clan")
    Clan clan;

    @Relationship("player")
    Player player;
}
