package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;

/**
 * @deprecated GlobaleaderboardEntry replaced with leaderboardRating
 */
@Deprecated
@Data
@Type("globalLeaderboardEntry")
public class GlobalLeaderboardEntry {
    @Id
    String id;
    String name;
    int rank;
    Double mean;
    Double deviation;
    Integer numGames;
    Boolean isActive;
    Double rating;
}
