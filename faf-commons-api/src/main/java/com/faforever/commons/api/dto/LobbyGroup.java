package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Value;

/**
 * @deprecated LobbyGroups are supposed to be replaced with role based security
 */

@Deprecated
@Value
@Type("lobbyGroup")
public class LobbyGroup {
    @Id
    String userId;
    LegacyAccessLevel accessLevel;
}
