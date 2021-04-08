package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@Type(VotingSubject.TYPE_NAME)
public class VotingSubject extends AbstractEntity {
  public static final String TYPE_NAME = "votingSubject";

  String subjectKey;
  String subject;
  Integer numberOfVotes;
  String topicUrl;
  OffsetDateTime beginOfVoteTime;
  OffsetDateTime endOfVoteTime;
  Integer minGamesToVote;
  String descriptionKey;
  String description;
  Boolean revealWinner;
  @Relationship("votes")
  List<Vote> votes;
  @Relationship("votingQuestions")
  List<VotingQuestion> votingQuestions;
}

