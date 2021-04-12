package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Type("player")
public class Player extends AbstractEntity {
    String login;
    @RestrictedVisibility("IsModerator")
    String email;
    String userAgent;
    @RestrictedVisibility("IsModerator")
    String steamId;
    @RestrictedVisibility("IsModerator")
    String recentIpAddress;
    @RestrictedVisibility("IsModerator")
    OffsetDateTime lastLogin;

    @Relationship("names")
    List<NameRecord> names;

    @Deprecated
    @Relationship("globalRating")
    GlobalRating globalRating;

    @Deprecated
    @Relationship("ladder1v1Rating")
    Ladder1v1Rating ladder1v1Rating;

    @Deprecated
    @Relationship("lobbyGroup")
    LobbyGroup lobbyGroup;

    @Relationship("bans")
    List<BanInfo> bans;

    @Relationship("avatarAssignments")
    @JsonIgnore
    List<AvatarAssignment> avatarAssignments;

    @JsonBackReference
    @Relationship("reporterOnModerationReports")
    Set<ModerationReport> reporterOnModerationReports;

    @Override
    public String toString() {
        return String.format("%s [id %s]", login, id);
    }
}
