package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("mod")
public class Mod extends AbstractEntity {
  private String displayName;
  private String author;

  @Relationship("uploader")
  private Player uploader;

  @Relationship("versions")
  private List<ModVersion> versions;

  @Relationship("latestVersion")
  private ModVersion latestVersion;

  @Relationship("reviewsSummary")
  private ModReviewsSummary modReviewsSummary;
}
