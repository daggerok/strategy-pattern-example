package com.github.daggerok;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("almost java 8 jupiter strategy pattern test")
class StrategyPatternFunctionalTest {

  private static BiFunction<Predicate<Integer>, Iterable<Integer>, Integer> totalBy =
      (selector, numbers) -> StreamSupport.stream(numbers.spliterator(), true)
                                          .filter(selector)
                                          .reduce(Integer::sum)
                                          .orElse(0);

  @Test void any() {
    int total = totalBy.apply(n -> true, asList(1, 2, 3, 4, 5));
    assertThat(total).isEqualTo(15);
  }

  @Test void even() {
    int total = totalBy.apply(n -> n % 2 == 0, asList(1, 2, 3, 4, 5));
    assertThat(total).isEqualTo(6);
  }

  @Test void odd() {
    int total = totalBy.apply(n -> n % 2 != 0, asList(1, 2, 3, 4, 5));
    assertThat(total).isEqualTo(9);
  }

  @Test void none() {
    int total = totalBy.apply(n -> false, asList(1, 2, 3, 4, 5));
    assertThat(total).isEqualTo(0);
  }
}
