package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder(toBuilder = true)
@Type("gameReview")
@EqualsAndHashCode(callSuper = true)
public class GameReview extends Review {

    @Relationship("game")
    Game game;
}
