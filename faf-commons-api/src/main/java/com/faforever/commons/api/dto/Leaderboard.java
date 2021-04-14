package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("leaderboard")
public class Leaderboard extends AbstractEntity {
  private String technical_name;
  private String name_key;
  private String description_key;
}
