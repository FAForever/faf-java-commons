package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"technicalName", "nameKey"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Type("leaderboard")
public class Leaderboard extends AbstractEntity {
  private String technicalName;
  private String nameKey;
  private String descriptionKey;
}
