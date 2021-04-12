package com.faforever.commons.api.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class TutorialTest {


  @Test
  void testTutorialToString() {
    Tutorial tutorial = Tutorial.builder().category(TutorialCategory.builder().build()).build();
    assertThat("correct to string", tutorial.toString(), is(notNullValue()));
  }

  @Test
  void testTutorialCategoryToString() {
    TutorialCategory tutorialCategory = getTutorialCategory();
    assertThat("correct to string", tutorialCategory.toString(),
      is(String.format("TutorialCategory(id=%s, categoryKey=%s, category=%s)", tutorialCategory.getId(),
        tutorialCategory.getCategoryKey(), tutorialCategory.getCategory())));
  }

  @Test
  void testTutorialEquals() {
    Tutorial tutorial = Tutorial.builder().build();
    Tutorial secondTutorial = Tutorial.builder().build();
    assertThat("tutorial equals", tutorial.equals(secondTutorial), is(true));
  }

  @Test
  void testTutorialHashCode() {
    Tutorial tutorial = Tutorial.builder().build();
    Tutorial secondTutorial = Tutorial.builder().build();
    assertThat("tutorial equals", tutorial.hashCode(), is(secondTutorial.hashCode()));
  }

  @Test
  void testTutorialCategoryEquals() {
    TutorialCategory tutorialCategory = getTutorialCategory();
    TutorialCategory secondTutorialCategory = getTutorialCategory();
    assertThat("tutorial equals", tutorialCategory.equals(secondTutorialCategory), is(true));
  }

  @Test
  void testTutorialCategoryHashCode() {
    TutorialCategory tutorialCategory = getTutorialCategory();
    TutorialCategory secondTutorialCategory = getTutorialCategory();
    assertThat("tutorial equals", tutorialCategory.hashCode(), is(secondTutorialCategory.hashCode()));
  }

  @NotNull
  private TutorialCategory getTutorialCategory() {
    return TutorialCategory.builder()
      .id("fixedId")
      .category("fixedCategory")
      .categoryKey("fixedCategoryKey")
      .build();
  }

}
