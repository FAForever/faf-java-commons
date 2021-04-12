package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Type("avatarAssignment")
public class AvatarAssignment extends AbstractEntity {
  Boolean selected;
  OffsetDateTime expiresAt;
  @Relationship("player")
  @JsonIgnore
  Player player;
  @Relationship("avatar")
  @JsonIgnore
  Avatar avatar;
}
