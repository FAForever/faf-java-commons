package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.List;

@Value
@Builder
@ToString(exclude = {"playerStats", "reviews", "gameReviewsSummary"})
@EqualsAndHashCode(of = "id")
@Type("game")
public class Game implements ElideEntity {
    @Id
    String id;
    String name;
    Boolean replayAvailable;
    OffsetDateTime startTime;
    OffsetDateTime endTime;
    Integer replayTicks;
    Validity validity;
    VictoryCondition victoryCondition;

    @Relationship("reviews")
    List<GameReview> reviews;

    @Relationship("playerStats")
    List<GamePlayerStats> playerStats;

    @Relationship("host")
    Player host;

    @Relationship("featuredMod")
    FeaturedMod featuredMod;

    @Relationship("mapVersion")
    MapVersion mapVersion;

    @Relationship("reviewsSummary")
    GameReviewsSummary gameReviewsSummary;
}
