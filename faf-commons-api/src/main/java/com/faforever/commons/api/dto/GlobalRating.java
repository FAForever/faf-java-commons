package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @deprecated GlobalRating replaced with leaderboardRating
 */
@Deprecated
@ToString(callSuper = true)
@Type("globalRating")
@EqualsAndHashCode(callSuper = true)
public class GlobalRating extends Rating {
}
