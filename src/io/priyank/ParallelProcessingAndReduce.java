package io.priyank;

import java.util.List;
import java.util.Arrays;

public class ParallelProcessingAndReduce {

  public static Integer add(Integer a, Integer b) {
    Integer result = a + b;
    System.out.println("a: " + a + " b: " + b + " Result: " + result + " T: " + Thread.currentThread());
    return result;
  }
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    System.out.println(numbers
        .stream()
        .reduce(0, ParallelProcessingAndReduce::add));

    System.out.println(numbers
        .parallelStream()
        .reduce(0, ParallelProcessingAndReduce::add));

    System.out.println(numbers
        .parallelStream()
        .reduce(30, ParallelProcessingAndReduce::add));
  }
}
