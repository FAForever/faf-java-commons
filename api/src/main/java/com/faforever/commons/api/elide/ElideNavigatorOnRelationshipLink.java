package com.faforever.commons.api.elide;

/**
 * Terminal navigator pointing at a JSON:API relationship endpoint
 * ({@code /data/{type}/{id}/relationships/{name}}). It only exposes {@link #build()} because a relationship
 * link addresses the linkage itself (read or modify via PATCH/POST/DELETE) and cannot be navigated further.
 */
@FunctionalInterface
public interface ElideNavigatorOnRelationshipLink {
  String build();
}
