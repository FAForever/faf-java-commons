package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
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
