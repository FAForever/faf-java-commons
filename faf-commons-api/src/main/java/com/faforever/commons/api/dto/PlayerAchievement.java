package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("playerAchievement")
public class PlayerAchievement extends AbstractEntity {
    AchievementState state;
    Integer currentSteps;

    @Relationship("achievement")
    AchievementDefinition achievement;
}
