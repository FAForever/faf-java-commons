package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("mapVersionStatistics")
public class MapVersionStatistics extends AbstractEntity {
    private int downloads;
    private int draws;
    private int plays;

    @Relationship("mapVersion")
    private MapVersion mapVersion;
}
