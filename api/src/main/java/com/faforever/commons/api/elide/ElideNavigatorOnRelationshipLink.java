package com.faforever.commons.api.elide;

/**
 * Terminal navigator pointing at a JSON:API relationship endpoint
 * ({@code /data/{type}/{id}/relationships/{name}}). It only exposes {@link #build()} and the related entity
 * type, because a relationship link addresses the linkage itself (read or modify via PATCH/POST/DELETE) and
 * cannot be navigated further. {@code T} is the type of the related resource(s) the linkage points at.
 */
public interface ElideNavigatorOnRelationshipLink<T extends ElideEntity> {
  String build();

  Class<T> getDtoClass();
}
