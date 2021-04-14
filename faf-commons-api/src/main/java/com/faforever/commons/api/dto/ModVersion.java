package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.net.URL;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("modVersion")
public class ModVersion extends AbstractEntity {
  private String uid;
  private ModType type;
  private String description;
  private ComparableVersion version;
  private String filename;
  private String icon;
  private boolean ranked;
  private boolean hidden;
  private URL thumbnailUrl;
  private URL downloadUrl;

  @Relationship("mod")
  private Mod mod;

  @Relationship("reviews")
  private List<ModVersionReview> reviews;

  @Relationship("reviewsSummary")
  private ModVersionReviewsSummary modVersionReviewsSummary;
}
