package com.faforever.commons.api.elide;

public interface ElideNavigatorOnId<T extends ElideEntity> extends ElideEndpointBuilder<T>{

  ElideNavigatorOnId<T> addInclude(String include);

  <R extends ElideEntity> ElideNavigatorSelector<R> navigateRelationship(Class<R> entityClass, String name);

  /**
   * Points the navigator at the JSON:API relationship endpoint
   * ({@code /data/{type}/{id}/relationships/{name}}), used to read or modify the relationship linkage itself
   * (e.g. PATCH/POST/DELETE to add, replace or remove members). This differs from
   * {@link #navigateRelationship(Class, String)}, which addresses the related resource(s).
   *
   * <p>This is a terminal operation: the returned navigator only allows {@link
   * ElideNavigatorOnRelationshipLink#build()}. Includes are not allowed on the parent.
   */
  ElideNavigatorOnRelationshipLink relationshipLink(String name);

}
