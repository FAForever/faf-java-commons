package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@EqualsAndHashCode(of = "id")
@Type("coopMission")
public class CoopMission {
    @Id
    String id;
    String name;
    int version;
    String category;
    String thumbnailUrlSmall;
    String thumbnailUrlLarge;
    String description;
    String downloadUrl;
    String folderName;
}
