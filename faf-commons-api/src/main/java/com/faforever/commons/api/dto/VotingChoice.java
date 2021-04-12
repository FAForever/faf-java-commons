package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Type(VotingChoice.TYPE_NAME)
public class VotingChoice extends AbstractEntity {
  public static final String TYPE_NAME = "votingChoice";

  String choiceTextKey;
  String choiceText;
  String descriptionKey;
  String description;
  Integer numberOfAnswers;
  Integer ordinal;
  @Relationship("votingQuestion")
  VotingQuestion votingQuestion;
}
