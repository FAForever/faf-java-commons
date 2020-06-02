package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Type("teamkill")
@RestrictedVisibility("IsModerator")
@EqualsAndHashCode(callSuper = true)
public class Teamkill extends AbstractEntity {
    @Relationship("teamkiller")
    private Player teamkiller;
    @Relationship("victim")
    private Player victim;
    @Relationship("game")
    private Game game;
    private long gameTime;
    private OffsetDateTime reportedAt;
}
