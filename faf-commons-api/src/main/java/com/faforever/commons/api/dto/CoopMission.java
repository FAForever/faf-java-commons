package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"id", "name", "version"})
@EqualsAndHashCode(of = "id")
@Type("coopMission")
public class CoopMission {
  @Id
  private String id;
  private String name;
  private int version;
  private String category;
  private String thumbnailUrlSmall;
  private String thumbnailUrlLarge;
  private String description;
  private String downloadUrl;
  private String folderName;
}
