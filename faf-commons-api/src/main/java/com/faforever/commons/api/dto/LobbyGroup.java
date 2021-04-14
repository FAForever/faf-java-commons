package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;

/**
 * @deprecated LobbyGroups are supposed to be replaced with role based security
 */

@Deprecated
@Data
@Type("lobbyGroup")
public class LobbyGroup {
  @Id
  private String userId;
  private LegacyAccessLevel accessLevel;
}
