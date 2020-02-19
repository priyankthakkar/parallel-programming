package io.priyank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelAndSequential {

  public static Integer transform(Integer number) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return number * 1;
  }

  // run everything sequentially
  public static void use(Stream<Integer> stream) {
    stream
            .map(ParallelAndSequential::transform)
            .forEach(System.out::println);
  }

  // runs in parallel as expected
  public static void useParallel(Stream<Integer> stream) {
    stream
            .parallel()
            .map(ParallelAndSequential::transform)
            .forEach(System.out::println);
  }

  /**
   * here, entire code runs sequentially. Regardless of mentioning parallel before map and sequential before forEach,
   * the last mentioned way will be considered. Which in this case is sequential (last mentioned before forEach.)
   *
   * @param stream
   */
  public static void useParallelAndSequential(Stream<Integer> stream) {
    stream
            .parallel()
            .map(ParallelAndSequential::transform)
            .sequential()
            .forEach(System.out::println);
  }

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    useParallel(numbers.stream());
  }
}
