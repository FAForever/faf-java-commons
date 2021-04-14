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
  private Boolean selected;
  private OffsetDateTime expiresAt;
  @Relationship("player")
  @JsonIgnore
  private Player player;
  @Relationship("avatar")
  @JsonIgnore
  private Avatar avatar;
}
