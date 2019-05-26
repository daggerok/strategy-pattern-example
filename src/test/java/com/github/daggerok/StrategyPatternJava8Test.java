package com.github.daggerok;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("almost java 8 jupiter strategy pattern test")
class StrategyPatternJava8Test {

  private static class Strategy {
    static int totalBy(Predicate<Integer> predicate, int... numbers) {
      int total = 0;
      for (int number : numbers) {
        if (predicate.test(number)) {
          total += number;
        }
      }
      return total;
    }
  }

  @Test void any() {
    int total = Strategy.totalBy(n -> true, 1, 2, 3, 4, 5);
    assertThat(total).isEqualTo(15);
  }

  @Test void even() {
    int total = Strategy.totalBy(n -> n % 2 == 0, 1, 2, 3, 4, 5);
    assertThat(total).isEqualTo(6);
  }

  @Test void odd() {
    int total = Strategy.totalBy(n -> n % 2 != 0, 1, 2, 3, 4, 5);
    assertThat(total).isEqualTo(9);
  }

  @Test void none() {
    int total = Strategy.totalBy(n -> false, 1, 2, 3, 4, 5);
    assertThat(total).isEqualTo(0);
  }
}
