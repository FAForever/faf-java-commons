package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Type(MeResult.TYPE_NAME)
@Value
@SuperBuilder(toBuilder = true)
public class MeResult implements ElideEntity {

  public static final String TYPE_NAME = "me";

  @Id
  String userId;
  String userName;
  String email;
  Clan clan;
  Set<String> groups;
  Set<String> permissions;

  @Override
  public String getId() {
    return userId;
  }

  @Value
  public static class Clan {
    Integer id;
    Integer membershipId;
    String tag;
    String name;
  }
}
