package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
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
