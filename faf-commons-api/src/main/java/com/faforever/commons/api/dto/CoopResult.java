package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Duration;

@Value
@Builder
@EqualsAndHashCode(of = "id")
@Type("coopResult")
public class CoopResult {
    @Id
    String id;
    Duration duration;
    String playerNames;
    boolean secondaryObjectives;
    /** This field is not provided by the API but must be enriched instead. */
    int ranking;
    int playerCount;

    @Relationship("game")
    Game game;
}
