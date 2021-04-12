package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Value
@SuperBuilder(toBuilder = true)
@Type("teamkill")
@RestrictedVisibility("IsModerator")
public class Teamkill implements ElideEntity {
    @Id
    String id;
    @Relationship("teamkiller")
    Player teamkiller;
    @Relationship("victim")
    Player victim;
    @Relationship("game")
    Game game;
    long gameTime;
    OffsetDateTime reportedAt;
}
