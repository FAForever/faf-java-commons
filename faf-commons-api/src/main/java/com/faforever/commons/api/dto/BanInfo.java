package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.OffsetDateTime;

@Type("banInfo")
@RestrictedVisibility("HasBanRead")
@Data
@ToString(of = {"player", "author", "level", "duration", "banStatus"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BanInfo extends AbstractEntity {
  @Relationship("player")
  @JsonIgnore
  private Player player;
  @Relationship("author")
  @JsonIgnore
  private Player author;
  private String reason;
  private OffsetDateTime expiresAt;
  private BanLevel level;
  @Relationship("moderationReport")
  @JsonIgnore
  private ModerationReport moderationReport;
  private String revokeReason;
  @Relationship("revokeAuthor")
  @JsonIgnore
  private Player revokeAuthor;
  private OffsetDateTime revokeTime;
  @JsonIgnore
  private BanDurationType duration;
  @JsonIgnore
  private BanStatus banStatus;

  @JsonIgnore
  public BanDurationType getDuration() {
    return expiresAt == null ? BanDurationType.PERMANENT : BanDurationType.TEMPORARY;
  }

  @JsonIgnore
  public BanStatus getBanStatus() {
    if (revokeTime != null && revokeTime.isBefore(OffsetDateTime.now())) {
      return BanStatus.DISABLED;
    }
    if (getDuration() == BanDurationType.PERMANENT) {
      return BanStatus.BANNED;
    }
    return expiresAt.isAfter(OffsetDateTime.now())
      ? BanStatus.BANNED
      : BanStatus.EXPIRED;
  }
}
