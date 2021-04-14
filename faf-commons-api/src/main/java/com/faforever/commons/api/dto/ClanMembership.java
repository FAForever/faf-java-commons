package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"clan", "player"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Type("clanMembership")
public class ClanMembership extends AbstractEntity {
  @Relationship("clan")
  private Clan clan;

  @Relationship("player")
  private Player player;
}
