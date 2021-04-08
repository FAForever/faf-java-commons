package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@Type(Vote.TYPE_NAME)
public class Vote extends AbstractEntity {
  public static final String TYPE_NAME = "vote";

  @Relationship("player")
  Player player;
  @Relationship("votingSubject")
  VotingSubject votingSubject;
  @Relationship("votingAnswers")
  List<VotingAnswer> votingAnswers;
}
