package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Review extends AbstractEntity {
    private String text;
    private Byte score;

    @Relationship("player")
    private Player player;
}
