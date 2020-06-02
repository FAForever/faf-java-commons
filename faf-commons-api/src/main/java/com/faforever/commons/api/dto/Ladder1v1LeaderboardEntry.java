package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("ladder1v1LeaderboardEntry")
public class Ladder1v1LeaderboardEntry extends AbstractEntity{
    private int rank;
    private String name;
    private Double mean;
    private Double deviation;
    private Integer numGames;
    private Integer wonGames;
    private Boolean isActive;
    private Double rating;
}
