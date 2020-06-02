package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Type("userNote")
@RestrictedVisibility("IsModerator")
@EqualsAndHashCode(callSuper = true)
public class UserNote extends AbstractEntity {
    @Relationship("player")
    private Player player;
    @Relationship("author")
    private Player author;
    private boolean watched;
    private String note;
}
