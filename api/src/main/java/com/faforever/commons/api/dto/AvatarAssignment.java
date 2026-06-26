package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Type("avatarAssignment")
public class AvatarAssignment extends AbstractEntity<AvatarAssignment> {
  /**
   * @deprecated The selected avatar is now tracked via {@link Player#getCurrentAvatar()}
   *     (the {@code login.avatar_id} column). Read/update that relationship instead; this flag is
   *     kept only for backwards compatibility with older clients and will be removed.
   */
  @Deprecated(forRemoval = true)
  @ToString.Include
  private Boolean selected;
  private OffsetDateTime expiresAt;
  @Relationship("player")
  @JsonIgnore
  private Player player;
  @Relationship("avatar")
  @JsonIgnore
  private Avatar avatar;
}
