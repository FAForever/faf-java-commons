package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("coopResult")
public class CoopResult extends AbstractEntity{
    @Id
    private String id;
    private Duration duration;
    private String playerNames;
    private boolean secondaryObjectives;
    private int ranking;
    private int playerCount;
}
