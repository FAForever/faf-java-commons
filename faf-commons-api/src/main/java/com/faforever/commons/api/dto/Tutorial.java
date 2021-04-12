package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Type("tutorial")
public class Tutorial extends AbstractEntity {
  String descriptionKey;
  String description;
  String titleKey;
  String title;
  @Relationship("category")
  TutorialCategory category;
  String image;
  String imageUrl;
  int ordinal;
  boolean launchable;
  String technicalName;
  @Relationship("mapVersion")
  MapVersion mapVersion;
}
