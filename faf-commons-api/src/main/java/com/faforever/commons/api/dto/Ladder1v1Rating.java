package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @deprecated Ladder1v1Rating replaced with leaderboardRating
 */
@Deprecated
@SuperBuilder
@Type("ladder1v1Rating")
@EqualsAndHashCode(callSuper = true)
public class Ladder1v1Rating extends Rating {
}
