package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Type("playerAchievement")
public class PlayerAchievement extends AbstractEntity {
    AchievementState state;
    Integer currentSteps;

    @Relationship("achievement")
    AchievementDefinition achievement;
}
