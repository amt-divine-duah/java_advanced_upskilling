/**
 * Parallel Summation of an Array (Increased Complexity)
 */
package labs.lab_one.concurrency_topics.array_summation;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

import static java.util.Arrays.copyOfRange;

public class ArraySummation extends RecursiveTask<Long> {
    private long[] array;

    public ArraySummation(long[] array) {
        this.array = array;
    }

    @Override
    protected Long compute() {
        // base case
        if (array.length <= 1000) {
            // sum the array using stream
            return Arrays.stream(array).sum();
        }
        // recursive case
        int mid = array.length / 2;
        ArraySummation task1 = new ArraySummation(copyOfRange(array, 0, mid));
        task1.fork();
        ArraySummation task2 = new ArraySummation(copyOfRange(array, mid, array.length));
        task2.fork();
        return task1.join() + task2.join();
    }
}
