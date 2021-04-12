package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.List;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Type("mapVersion")
public class MapVersion extends AbstractEntity {
  String description;
  Integer maxPlayers;
  Integer width;
  Integer height;
  ComparableVersion version;
  String folderName;
  // TODO name consistently with folderName
  String filename;
  boolean ranked;
  boolean hidden;
  URL thumbnailUrlSmall;
  URL thumbnailUrlLarge;
  URL downloadUrl;

  @Relationship("map")
  Map map;

  @Relationship("statistics")
  MapVersionStatistics statistics;

  @Nullable
  @Relationship("ladder1v1Map")
  Ladder1v1Map ladder1v1Map;

  @Relationship("reviews")
  List<MapVersionReview> reviews;
}
