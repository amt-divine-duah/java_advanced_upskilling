package labs.lab_one.concurrency_topics.fibonacci;

import java.util.concurrent.ForkJoinPool;

public class FibonacciMain {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int number = 40;
        FibonacciSequence task = new FibonacciSequence(number);
        int result = pool.invoke(task);
        System.out.println("Fibonacci of " + number + " is " + result);
    }
}