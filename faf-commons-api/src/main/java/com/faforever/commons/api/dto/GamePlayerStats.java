package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.List;

@Value
@EqualsAndHashCode(of = "id")
@Type("gamePlayerStats")
public class GamePlayerStats implements ElideEntity {
    @Id
    String id;
    boolean ai;
    Faction faction;
    byte color;
    byte team;
    byte startSpot;
    @Deprecated
    Float beforeMean;
    @Deprecated
    Float beforeDeviation;
    @Deprecated
    Float afterMean;
    @Deprecated
    Float afterDeviation;
    byte score;
    @Nullable
    OffsetDateTime scoreTime;

    @Relationship("game")
    Game game;

    @Relationship("player")
    Player player;

    @Relationship("ratingChanges")
    private List<LeaderboardRatingJournal> leaderboardRatingJournals;
}
