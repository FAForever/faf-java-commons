package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
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
