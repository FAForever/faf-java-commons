package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Type(UserGroup.TYPE_NAME)
public class UserGroup extends AbstractEntity {

  public static final String TYPE_NAME = "userGroup";

  String technicalName;
  String nameKey;
  boolean public_;
  @Relationship("members")
  @JsonIgnore
  Set<Player> members;
  @Relationship("permissions")
  Set<GroupPermission> permissions;
}
