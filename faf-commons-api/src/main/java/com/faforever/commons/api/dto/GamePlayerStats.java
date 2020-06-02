package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("gamePlayerStats")
public class GamePlayerStats extends AbstractEntity {
    private boolean ai;
    private Faction faction;
    private byte color;
    private byte team;
    private byte startSpot;
    private Float beforeMean;
    private Float beforeDeviation;
    private Float afterMean;
    private Float afterDeviation;
    private byte score;
    @Nullable
    private OffsetDateTime scoreTime;

    @Relationship("game")
    private Game game;

    @Relationship("player")
    private Player player;
}
