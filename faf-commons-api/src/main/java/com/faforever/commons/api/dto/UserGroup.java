package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@ToString(of = {"technicalName", "nameKey"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Type(UserGroup.TYPE_NAME)
public class UserGroup extends AbstractEntity {

  public static final String TYPE_NAME = "userGroup";

  private String technicalName;
  private String nameKey;
  private boolean public_;
  @Relationship("members")
  @JsonIgnore
  private Set<Player> members;
  @Relationship("permissions")
  private Set<GroupPermission> permissions;
}
