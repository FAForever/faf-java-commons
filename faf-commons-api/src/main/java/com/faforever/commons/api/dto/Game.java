package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("game")
public class Game extends AbstractEntity {
    private String name;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Validity validity;
    private VictoryCondition victoryCondition;

    @Relationship("reviews")
    private List<GameReview> reviews;

    @Relationship("playerStats")
    private List<GamePlayerStats> playerStats;

    @Relationship("host")
    private Player host;

    @Relationship("featuredMod")
    private FeaturedMod featuredMod;

    @Relationship("mapVersion")
    private MapVersion mapVersion;
}
