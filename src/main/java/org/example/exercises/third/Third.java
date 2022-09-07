package org.example.exercises.third;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Third {

    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Producer());
        executorService.submit(new Consumer());
        executorService.shutdown();
    }

    private static class Producer extends Random implements Runnable {

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void produce() throws InterruptedException {
            for (int i = 0; i < 10; i++) {
                queue.put(nextInt(100));
                Thread.sleep(1000);
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(queue.take());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


