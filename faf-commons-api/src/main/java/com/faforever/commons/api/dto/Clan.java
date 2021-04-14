package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = "memberships")
@EqualsAndHashCode(callSuper = true)
@Type("clan")
public class Clan extends AbstractEntity {
  private String name;
  private String tag;
  private String description;
  private String tagColor;
  private String websiteUrl;

  @Relationship("founder")
  private Player founder;

  @Relationship("leader")
  private Player leader;

  @Relationship("memberships")
  private List<ClanMembership> memberships;
}
