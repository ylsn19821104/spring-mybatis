package com.shihui.test.util;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;


public class Calculator extends RecursiveTask<Integer> {


    private static final int THRESHOLD = 100;
    private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        long startTime = System.currentTimeMillis();
        int sum = 0;
        if ((start - end) < THRESHOLD) {
            for (int i = start; i < end; i++)
                sum += i;
        } else {
            int middle = (start + end) / 2;
            Calculator left = new Calculator(start, middle);
            Calculator right = new Calculator(middle + 1, end);
            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }
        long endTime = System.currentTimeMillis();
        System.err.println("compute call time:" + (startTime - endTime));
        System.err.println("result:" + sum);
        return sum;
    }

    protected Integer calc() {
        long startTime = System.currentTimeMillis();
        int sum = 0;
        for (int i = start; i < end; i++)
            sum += i;
        long endTime = System.currentTimeMillis();
        System.err.println("calc call time:" + (startTime - endTime));
        return sum;
    }

    public static void main(String[] args) {
        try {
            run();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void run() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> rt = pool.submit(new Calculator(0, 100000000));
        new Calculator(0, 1000000).calc();
        System.err.println("rt:" + rt.get());
    }
}
