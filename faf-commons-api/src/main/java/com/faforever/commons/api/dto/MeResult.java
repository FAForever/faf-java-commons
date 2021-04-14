package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;

import java.util.Set;

@Type(MeResult.TYPE_NAME)
@Data
public class MeResult implements ElideEntity {

  public static final String TYPE_NAME = "me";

  @Id
  private String userId;
  private String userName;
  private String email;
  private Clan clan;
  private Set<String> groups;
  private Set<String> permissions;

  @Override
  public String getId() {
    return userId;
  }

  @Data
  public static class Clan {
    Integer id;
    Integer membershipId;
    String tag;
    String name;
  }
}
