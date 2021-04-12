package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @deprecated Ladder1v1LeaderboardEntry replaced with leaderboardRating
 */
@Deprecated
@Data
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
