package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Type(VotingQuestion.TYPE_NAME)
public class VotingQuestion extends AbstractEntity {
  public static final String TYPE_NAME = "votingQuestion";

  int numberOfAnswers;
  String question;
  String description;
  String questionKey;
  String descriptionKey;
  Integer maxAnswers;
  Integer ordinal;
  Boolean alternativeQuestion;
  @Relationship("votingSubject")
  VotingSubject votingSubject;
  @Relationship("winners")
  List<VotingChoice> winners;
  @Relationship("votingChoices")
  List<VotingChoice> votingChoices;

}
