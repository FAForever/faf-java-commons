package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"technicalName", "nameKey", "leaderboard"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Type("matchmakerQueue")
public class MatchmakerQueue extends AbstractEntity {
  private String technicalName;
  private String nameKey;

  @Relationship("featuredMod")
  private FeaturedMod featuredMod;

  @Relationship("leaderboard")
  private Leaderboard leaderboard;
}
