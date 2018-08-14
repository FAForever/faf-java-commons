package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Type("player")
public class Player extends AbstractEntity {
    private String login;
    @Relationship("names")
    List<NameRecord> names;
    @RestrictedVisibility("IsModerator")
    private String email;
    private String userAgent;
    @RestrictedVisibility("IsModerator")
    private String steamId;
    @RestrictedVisibility("IsModerator")
    private String recentIpAddress;

    @Relationship("globalRating")
    private GlobalRating globalRating;

    @Relationship("ladder1v1Rating")
    private Ladder1v1Rating ladder1v1Rating;

    @Relationship("lobbyGroup")
    private LobbyGroup lobbyGroup;

    @Relationship("bans")
    private List<BanInfo> bans;

    @Relationship("avatarAssignments")
    @JsonIgnore
    private List<AvatarAssignment> avatarAssignments;

    @JsonBackReference
    @Relationship("reporterOnModerationReports")
    private Set<ModerationReport> reporterOnModerationReports;

    @JsonBackReference
    @Relationship("reportedOnModerationReports")
    private Set<ModerationReport> reportedOnModerationReports;

    @Override
    public String toString() {
        return String.format("%s [id %s]", login, id);
    }
}
