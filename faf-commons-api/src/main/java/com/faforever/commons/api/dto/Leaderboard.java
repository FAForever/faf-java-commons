package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("leaderboard")
public class Leaderboard extends AbstractEntity {

    String technical_name;
    String name_key;
    String description_key;

}
