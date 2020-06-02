package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("globalLeaderboardEntry")
public class GlobalLeaderboardEntry extends AbstractEntity{
    private String name;
    private int rank;
    private Double mean;
    private Double deviation;
    private Integer numGames;
    private Boolean isActive;
    private Double rating;
}
