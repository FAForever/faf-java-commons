package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(of = "id")
public class Rating {
    @Id
    String id;
    double mean;
    double deviation;
    double rating;

    @Relationship("player")
    Player player;
}
