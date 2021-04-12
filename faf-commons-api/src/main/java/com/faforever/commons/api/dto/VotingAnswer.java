package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Type(VotingAnswer.TYPE_NAME)
public class VotingAnswer extends AbstractEntity {
  public static final String TYPE_NAME = "votingAnswer";

  @Relationship("vote")
  Vote vote;
  Integer alternativeOrdinal;
  @Relationship("votingChoice")
  VotingChoice votingChoice;
}
