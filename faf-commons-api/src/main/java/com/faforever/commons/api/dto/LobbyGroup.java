package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * @deprecated LobbyGroups are supposed to be replaced with role based security
 */

@Deprecated
@Value
@SuperBuilder(toBuilder = true)
@Type("lobbyGroup")
public class LobbyGroup {
    @Id
    String userId;
    LegacyAccessLevel accessLevel;
}
