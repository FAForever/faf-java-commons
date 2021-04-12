package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;

@Data
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
