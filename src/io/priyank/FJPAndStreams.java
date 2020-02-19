package io.priyank;

import java.util.List;
import java.util.Arrays;

public class FJPAndStreams {

  public static Integer transform(Integer number) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Number: " + number + " Thread: " + Thread.currentThread());
    return number * 1;
  }


  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    // running code sequentially shows that each number in the list processed by the main thread
    numbers
            .stream()
            .map(FJPAndStreams::transform)
            .forEach(number -> {
            });

    // While running the code in parallel, the number are processed on the different thread
    // main thread participates in the processing along with other threads
    // The thread pool used is CommonForkJoinPool introduced in Java 7
    // It implements the work stealing methodology and solve the problem of Thread induced deadlock.
    // Thread induced deadlock - when computational intensive threads are just waiting on each other to finish and
    // no thread finishes work (in this scenario main thread doesn't participate)
    numbers
            .parallelStream()
            .map(FJPAndStreams::transform)
            .forEach(number -> {

            });
  }
}
