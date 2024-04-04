package labs.lab_one.concurrency_topics.array_summation;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ArraySummationMain {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();

        Random random = new Random();
        int size = 10000;
        long[] array = new long[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextLong(0,20);
        }

        ArraySummation task = new ArraySummation(array);
        Long result = pool.invoke(task);

        System.out.println("This is the result " + result);
    }
}
