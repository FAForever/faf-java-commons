package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * @deprecated Ladder1v1LeaderboardEntry replaced with leaderboardRating
 */
@Deprecated
@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@Type("ladder1v1LeaderboardEntry")
public class Ladder1v1LeaderboardEntry {
    @Id
    String id;
    int rank;
    String name;
    Double mean;
    Double deviation;
    Integer numGames;
    Integer wonGames;
    Boolean isActive;
    Double rating;
}
