package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@Value
@Builder
@Type("tutorialCategory")
@ToString(exclude={"tutorials"})
@EqualsAndHashCode(exclude={"tutorials"})
public class TutorialCategory implements ElideEntity {
  @Id
  String id;
  String categoryKey;
  String category;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Relationship("tutorials")
  List<Tutorial> tutorials;

}
