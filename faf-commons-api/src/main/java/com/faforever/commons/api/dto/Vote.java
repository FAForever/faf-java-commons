package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Type(Vote.TYPE_NAME)
public class Vote extends AbstractEntity {
  public static final String TYPE_NAME = "vote";

  @Relationship("player")
  private Player player;
  @Relationship("votingSubject")
  private VotingSubject votingSubject;
  @Relationship("votingAnswers")
  private List<VotingAnswer> votingAnswers;
}
