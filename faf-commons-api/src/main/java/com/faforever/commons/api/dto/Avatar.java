package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Type("avatar")
@Value
@EqualsAndHashCode(callSuper = true)
public class Avatar extends AbstractEntity {
    String url;
    String tooltip;
    @Relationship("assignments")
    @JsonIgnore
    List<AvatarAssignment> assignments;
}
