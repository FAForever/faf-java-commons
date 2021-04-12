package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
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
