package com.faforever.commons.api.dto;

import com.faforever.commons.api.elide.ElideEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@RestrictedVisibility("IsModerator")
@Value
@Builder
@EqualsAndHashCode(of = "domain")
@Type("domainBlacklist")
public class DomainBlacklist implements ElideEntity {
  @Id
  String domain;

  @Override
  @JsonIgnore
  public String getId() {
    return domain;
  }
}
