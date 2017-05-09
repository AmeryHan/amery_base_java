package amery.jdk.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    private int compute(int small) {
        final int[] results = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        return results[small];
    }

    public Integer compute() {
        if (n <= 10) {
            return compute(n);
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        Fibonacci f2 = new Fibonacci(n - 2);
        f1.fork();
        f2.fork();
        return f1.join() + f2.join();
    }
    
    @Test
    public void testFibonacci() throws InterruptedException, ExecutionException {
        ForkJoinTask<Integer> fjt = new Fibonacci(45);
        ForkJoinPool fjpool = new ForkJoinPool();
        Future<Integer> result = fjpool.submit(fjt);

        // do something
        System.out.println(result.get());
    }
}
