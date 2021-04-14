package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Type("avatar")
@Data
@ToString(of = {"url"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Avatar extends AbstractEntity {
  private String url;
  private String tooltip;
  @Relationship("assignments")
  @JsonIgnore
  private List<AvatarAssignment> assignments;
}
