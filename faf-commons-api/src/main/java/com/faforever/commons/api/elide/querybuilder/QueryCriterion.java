package com.faforever.commons.api.elide.querybuilder;

import com.faforever.commons.api.elide.ElideEntity;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface QueryCriterion<T> {
  default String getId() {
    return getRootClass().getSimpleName() + "::" + getApiName();
  }

  Class<? extends ElideEntity> getRootClass();

  QueryCriterion<T> setRootClass(Class<? extends ElideEntity> rootClass);

  String getApiName();

  QueryCriterion<T> setApiName(String apiName);

  Class<T> getValueType();

  QueryCriterion<T> setValueType(Class<T> valueType);

  Set<QueryOperator> getSupportedOperators();

  QueryCriterion<T> setSupportedOperators(Set<QueryOperator> supportedOperators);

  List<T> getProposals();

  QueryCriterion<T> setProposals(List<T> proposals);

  boolean isAllowsOnlyProposedValues();

  QueryCriterion<T> setAllowsOnlyProposedValues(boolean allowsOnlyProposedValues);

  boolean isAdvancedFilter();

  QueryCriterion<T> setAdvancedFilter(boolean value);

  int getOrder();

  QueryCriterion<T> setOrder(int value);

  default String createRsql(QueryOperator operator, T[] elements) {
    if (!Objects.equals(getValueType(), elements.getClass().getComponentType())) {
      throw new IllegalArgumentException("Array of elements must be of type " + getValueType());
    }

    operator.validateElementAmount(elements.length);

    if (!getSupportedOperators().contains(operator)) {
      throw new IllegalArgumentException(MessageFormat.format("The operator `{0}` is not allowed", operator));
    }

    if (isAllowsOnlyProposedValues()) {
      List<T> disallowedElements = Stream.of(elements)
        .filter(e -> !getProposals().contains(e))
        .collect(Collectors.toList());

      if (disallowedElements.size() > 0) {
        throw new IllegalArgumentException(MessageFormat.format("There are values that are not allowed: {0}",
          disallowedElements.stream()
            .map(Objects::toString)
            .collect(Collectors.joining(", "))));
      }
    }

    return getApiName() + operator.getRsqlOperator() + Stream.of(elements)
      .map(Objects::toString)
      .collect(Collectors.joining("\",\"", "(\"", "\")"));
  }
}
