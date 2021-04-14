package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "version", "name", "md5", "url"})
@Type("featuredModFile")
public class FeaturedModFile {
  @Id
  private String id;
  private String version;
  private String group;
  private String name;
  private String md5;
  private String url;
}
