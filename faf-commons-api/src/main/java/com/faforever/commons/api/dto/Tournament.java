package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Type(TeamkillReport.TYPE_NAME)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@RestrictedVisibility("tournament")
@EqualsAndHashCode(callSuper = true)
public class Tournament extends AbstractEntity{
  private String name;
  private String description;
  @JsonProperty("tournament_type")
  private String tournamentType;
  @JsonProperty("created_at")
  private OffsetDateTime createdAt;
  @JsonProperty("participants_count")
  private int participantCount;
  @JsonProperty("start_at")
  private OffsetDateTime startingAt;
  @JsonProperty("completed_at")
  private OffsetDateTime completedAt;
  @JsonProperty("full_challonge_url")
  private String challongeUrl;
  @JsonProperty("live_image_url")
  private String liveImageUrl;
  @JsonProperty("sign_up_url")
  private String signUpUrl;
  @JsonProperty("open_signup")
  private boolean openForSignup;
}
