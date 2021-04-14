package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"achievement", "state"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Type("playerAchievement")
public class PlayerAchievement extends AbstractEntity {
  private AchievementState state;
  private Integer currentSteps;

  @Relationship("achievement")
  private AchievementDefinition achievement;
}
