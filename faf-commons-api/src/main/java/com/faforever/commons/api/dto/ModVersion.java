package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.net.URL;
import java.util.List;

@Data
@ToString(exclude = {"mod", "reviews", "modVersionReviewsSummary"})
@EqualsAndHashCode(callSuper = true)
@Type("modVersion")
public class ModVersion extends AbstractEntity {
  String uid;
  ModType type;
  String description;
  ComparableVersion version;
  String filename;
  String icon;
  boolean ranked;
  boolean hidden;
  URL thumbnailUrl;
  URL downloadUrl;

  @Relationship("mod")
  Mod mod;

  @Relationship("reviews")
  List<ModVersionReview> reviews;

  @Relationship("reviewsSummary")
  ModVersionReviewsSummary modVersionReviewsSummary;
}
