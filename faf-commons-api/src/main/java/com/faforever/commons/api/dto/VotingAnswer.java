package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Type(VotingAnswer.TYPE_NAME)
@EqualsAndHashCode(callSuper = true)
public class VotingAnswer extends AbstractEntity {
  public static final String TYPE_NAME = "votingAnswer";

  @Relationship("vote")
  private Vote vote;
  private Integer alternativeOrdinal;
  @Relationship("votingChoice")
  private VotingChoice votingChoice;
}
