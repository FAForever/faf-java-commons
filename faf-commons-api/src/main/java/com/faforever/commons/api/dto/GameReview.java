package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@Type("gameReview")
@EqualsAndHashCode(callSuper = true)
public class GameReview extends Review {

    @Relationship("game")
    Game game;
}
