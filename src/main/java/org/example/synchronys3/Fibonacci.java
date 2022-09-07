package org.example.synchronys3;

public class Fibonacci {

    private static int prev = 0;
    private static int cur = 1;

    public static void main(String[] args) {

        System.out.println(prev);
        System.out.println(cur);

        Thread th1 = new Thread(new FibonacciRunner());
        th1.start();

        Thread th2 = new Thread(new FibonacciRunner());
        th2.start();
    }

    private static synchronized void calcNext() {
        int next = prev + cur;
        prev = cur;
        cur = next;
        System.out.println(cur);
    }

    private static class FibonacciRunner implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                calcNext();
            }
        }
    }
}
