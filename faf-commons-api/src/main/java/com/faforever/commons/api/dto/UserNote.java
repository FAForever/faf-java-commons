package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("userNote")
@RestrictedVisibility("IsModerator")
public class UserNote extends AbstractEntity {
    @Relationship("player")
    Player player;
    @Relationship("author")
    Player author;
    boolean watched;
    String note;
}
