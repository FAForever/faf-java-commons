package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @deprecated GlobalRating replaced with leaderboardRating
 */
@Deprecated
@SuperBuilder
@Type("globalRating")
@EqualsAndHashCode(callSuper = true)
public class GlobalRating extends Rating {
}
