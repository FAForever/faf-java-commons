package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Type("achievement")
@Value
@SuperBuilder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id"})
public class AchievementDefinition implements ElideEntity {

    @Id
    String id;
    String description;
    int experiencePoints;
    AchievementState initialState;
    String name;
    String revealedIconUrl;
    Integer totalSteps;
    AchievementType type;
    String unlockedIconUrl;
    int order;
}
