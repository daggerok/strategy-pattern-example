package com.github.daggerok;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("java 7 jupiter strategy pattern test")
class StrategyPatternJava7Test {

  private interface Predicate<T> {
    boolean test(T t);
  }

  private static class Any implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
      return true;
    }
  }

  private static class Even implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
      return integer % 2 == 0;
    }
  }

  private static class Odd implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
      return integer % 2 != 0;
    }
  }

  private static class None implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
      return false;
    }
  }

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
    int total = Strategy.totalBy(new Any(), 1, 2, 3, 4, 5);
    assertThat(total).isEqualTo(15);
  }

  @Test void even() {
    int total = Strategy.totalBy(new Even(), 1, 2, 3, 4, 5);
    assertThat(total).isEqualTo(6);
  }

  @Test void odd() {
    int total = Strategy.totalBy(new Odd(), 1, 2, 3, 4, 5);
    assertThat(total).isEqualTo(9);
  }

  @Test void none() {
    int total = Strategy.totalBy(new None(), 1, 2, 3, 4, 5);
    assertThat(total).isEqualTo(0);
  }
}
