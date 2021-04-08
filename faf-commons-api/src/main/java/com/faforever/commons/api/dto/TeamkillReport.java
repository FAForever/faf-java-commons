package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
@Type(TeamkillReport.TYPE_NAME)
@RestrictedVisibility("IsModerator")
public class TeamkillReport extends AbstractEntity {

  public static final String TYPE_NAME = "teamkillReport";

  /**
   * How many seconds into the game, in simulation time.
   */
  Integer gameTime;
  OffsetDateTime reportedAt;

  @Relationship("teamkiller")
  Player teamkiller;

  @Relationship("victim")
  Player victim;

  @Relationship("game")
  Game game;
}
