package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@EqualsAndHashCode(of = "id")
@Type("featuredModFile")
public class FeaturedModFile {
    @Id
    String id;
    String version;
    String group;
    String name;
    String md5;
    String url;
}
