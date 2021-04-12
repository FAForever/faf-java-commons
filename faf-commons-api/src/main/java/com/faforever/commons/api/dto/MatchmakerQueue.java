package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Type("matchmakerQueue")
public class MatchmakerQueue extends AbstractEntity {

    String technicalName;
    String nameKey;

    @Relationship("featuredMod")
    FeaturedMod featuredMod;

    @Relationship("leaderboard")
    Leaderboard leaderboard;

}
