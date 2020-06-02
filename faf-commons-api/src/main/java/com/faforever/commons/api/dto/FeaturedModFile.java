package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Type("featuredModFile")
public class FeaturedModFile extends AbstractEntity{
    private String version;
    private String group;
    private String name;
    private String md5;
    private String url;
}
