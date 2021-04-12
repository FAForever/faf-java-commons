package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Type("mod")
public class Mod extends AbstractEntity {

  String displayName;
  String author;

  @Relationship("uploader")
  Player uploader;

  @Relationship("versions")
  List<ModVersion> versions;

  @Relationship("latestVersion")
  ModVersion latestVersion;

  @Relationship("reviewsSummary")
  ModReviewsSummary modReviewsSummary;
}
