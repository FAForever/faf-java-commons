package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;

/**
 * @deprecated GlobalRating replaced with leaderboardRating
 */
@Deprecated
@Type("globalRating")
@EqualsAndHashCode(callSuper = true)
public class GlobalRating extends Rating {
}
