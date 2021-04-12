package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Rating {
    @Id
    String id;
    double mean;
    double deviation;
    double rating;

    @Relationship("player")
    Player player;
}
