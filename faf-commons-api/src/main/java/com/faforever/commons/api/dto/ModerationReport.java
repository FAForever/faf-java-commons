package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Type("moderationReport")
@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ModerationReport extends AbstractEntity {
  String reportDescription;
  ModerationReportStatus reportStatus;
  String gameIncidentTimecode;
  String moderatorNotice;
  String moderatorPrivateNote;

  @Relationship("bans")
  Set<BanInfo> bans;
  @Relationship("reporter")
  Player reporter;
  @Relationship("game")
  Game game;
  @Relationship("lastModerator")
  Player lastModerator;
  @Relationship("reportedUsers")
  Set<Player> reportedUsers;
}
