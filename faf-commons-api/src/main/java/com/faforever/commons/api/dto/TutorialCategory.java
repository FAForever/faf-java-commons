package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Type("tutorialCategory")
@ToString(of = {"id", "categoryKey", "category"}, callSuper = true)
@EqualsAndHashCode(exclude={"tutorials"})
public class TutorialCategory implements ElideEntity {
  @Id
  private String id;
  private String categoryKey;
  private String category;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Relationship("tutorials")
  private List<Tutorial> tutorials;

}
