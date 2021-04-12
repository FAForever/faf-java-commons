package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Type("leaderboard")
public class Leaderboard extends AbstractEntity {

    String technical_name;
    String name_key;
    String description_key;

}
