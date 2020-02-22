package io.priyank;

import java.util.List;
import java.util.Arrays;

public class StreamsAndOrder {
  public static Integer transform(Integer number) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Transforming Number : " + number + " Thread " + Thread.currentThread());
    return number * 1;
  }

  public static void printIt(Integer number) {
    System.out.println("Printing Number : " + number + " Thread " + Thread.currentThread());
  }

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    // sequential processing, transforms one number then prints one number
    numbers
        .stream()
        .map(StreamsAndOrder::transform)
        .forEach(StreamsAndOrder::printIt);

    // parallel processing, transforming and printing operations run in parallel, hence  the order is not guaranteed
    // for the given processing
    numbers
        .parallelStream()
        .map(StreamsAndOrder::transform)
        .forEach(StreamsAndOrder::printIt);

    // parallel processing, transformation and printing is in parallel, yet the order is guaranteed. It is important
    // to note here that forEachOrdered is not sequential though the final outcome is ordered. It only makes sure
    // that the thread doesn't finish until the previous element is processed.

    // It is also important to remember that, ordered must be guaranteed by the underlying collection hence the
    // stream should guarantee the order. If stream doesn't guarantee the order, forEachOrdered or any ordered APIs
    // cannot guarantee the order of processing. e.g. List guarantees the order while set doesn't.
    numbers
        .parallelStream()
        .map(StreamsAndOrder::transform)
        .forEachOrdered(StreamsAndOrder::printIt);
  }
}
