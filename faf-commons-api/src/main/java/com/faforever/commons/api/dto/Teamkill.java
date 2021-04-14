package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@ToString(of = {"id"}, callSuper = true)
@Type("teamkill")
@RestrictedVisibility("IsModerator")
public class Teamkill implements ElideEntity {
  @Id
  private String id;
  @Relationship("teamkiller")
  private Player teamkiller;
  @Relationship("victim")
  private Player victim;
  @Relationship("game")
  private Game game;
  private long gameTime;
  private OffsetDateTime reportedAt;
}
