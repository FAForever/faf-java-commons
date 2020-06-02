package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Type(TeamkillReport.TYPE_NAME)
@RestrictedVisibility("IsModerator")
@EqualsAndHashCode(callSuper = true)
public class TeamkillReport extends AbstractEntity {

  public static final String TYPE_NAME = "teamkillReport";

  /**
   * How many seconds into the game, in simulation time.
   */
  private Integer gameTime;
  private OffsetDateTime reportedAt;

  @Relationship("teamkiller")
  private Player teamkiller;

  @Relationship("victim")
  private Player victim;

  @Relationship("game")
  private Game game;
}
