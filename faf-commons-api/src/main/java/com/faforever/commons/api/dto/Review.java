package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Review extends AbstractEntity {
    String text;
    Byte score;

    @Relationship("player")
    Player player;
}
