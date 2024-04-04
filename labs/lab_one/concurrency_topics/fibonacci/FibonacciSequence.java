/**
 * Consider a task to calculate the Fibonacci sequence of a large number.
 * This can be implemented as a ForkJoinTask where the task recursively
 * splits the calculation into smaller subproblems (calculating Fibonacci of smaller numbers)
 * until the base cases (numbers 1 and 0) are reached. The final results from these
 * subtasks are then combined to get the final Fibonacci number.
 *
 * The Fibonacci sequence is defined as follows:
 * F(number) = F(number-1) + F(number-2), where F(0) = 0 and F(1) = 1
 */
package labs.lab_one.concurrency_topics.fibonacci;

import java.util.concurrent.RecursiveTask;

class FibonacciSequence extends RecursiveTask<Integer> {

    private final int number;

    public FibonacciSequence(int number) {
        this.number = number;
    }

    @Override
    protected Integer compute() {
        if (number <= 1) {
            return number;
        }
        FibonacciSequence task1 = new FibonacciSequence(number - 1);
        task1.fork();
        FibonacciSequence task2 = new FibonacciSequence(number - 2);
        task2.fork();
        return task1.join() + task2.join();
    }
}

