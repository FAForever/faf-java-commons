package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder(toBuilder = true)
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
