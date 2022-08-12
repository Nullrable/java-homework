package com.lsd.lock;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author nhsoft.lsd
 */
public class ForkJoinTest {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CountTask countTask = new CountTask(1, 4);
        Future<Integer> future = forkJoinPool.submit(countTask);

        try {
            System.out.println(future.get());
        } catch (Exception e) {

        }

    }

    static class CountTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 2;
        private int start;
        private int end;

        public CountTask(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            //compute 是"计算"的意思
            int sum = 0;

            boolean canCompute = (end - start) <= THRESHOLD;
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                int middle = (start + end) / 2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle + 1, end);
                leftTask.fork();
                rightTask.fork();

                int leftResult = leftTask.join();
                int rightResult = rightTask.join();

                sum = leftResult + rightResult;
            }
            return sum;
        }
    }
}
