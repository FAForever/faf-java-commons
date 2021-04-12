package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(of = "id")
@Type("event")
public class Event {

    @Id
    String id;
    String name;
    String imageUrl;
    Type type;

    public enum Type {
        NUMERIC, TIME
    }
}
