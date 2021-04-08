package com.faforever.commons.api.dto;


import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Rating {
    @Id
    private String id;
    private double mean;
    private double deviation;
    private double rating;

    @Relationship("player")
    private Player player;
}
