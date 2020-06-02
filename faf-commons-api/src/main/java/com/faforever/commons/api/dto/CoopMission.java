package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("coopMission")
public class CoopMission extends AbstractEntity{

    private String name;
    private int version;
    private String category;
    private String thumbnailUrlSmall;
    private String thumbnailUrlLarge;
    private String description;
    private String downloadUrl;
    private String folderName;
}
