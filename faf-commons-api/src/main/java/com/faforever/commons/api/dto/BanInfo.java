package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.OffsetDateTime;

@Type("banInfo")
@RestrictedVisibility("HasBanRead")
@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class BanInfo extends AbstractEntity {
    @Relationship("player")
    @JsonIgnore
    Player player;
    @Relationship("author")
    @JsonIgnore
    Player author;
    String reason;
    OffsetDateTime expiresAt;
    BanLevel level;
    @Relationship("moderationReport")
    @JsonIgnore
    ModerationReport moderationReport;
    String revokeReason;
    @Relationship("revokeAuthor")
    @JsonIgnore
    Player revokeAuthor;
    OffsetDateTime revokeTime;
    @JsonIgnore
    BanDurationType duration;
    @JsonIgnore
    BanStatus banStatus;

    @JsonIgnore
    public BanDurationType getDuration() {
        return expiresAt == null ? BanDurationType.PERMANENT : BanDurationType.TEMPORARY;
    }

    @JsonIgnore
    public BanStatus getBanStatus() {
      if (revokeTime!=null && revokeTime.isBefore(OffsetDateTime.now())) {
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
