package org.example.exercises.forth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Forth {

    private static final Lock monitor = new ReentrantLock(false);
    public static AtomicInteger index = new AtomicInteger();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            service.submit(new RunnableImpl());
        }
        service.shutdown();
    }

    private static class RunnableImpl implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    monitor.lock();
                    if (index.get() <= 100) {
                        System.out.println(Thread.currentThread().getName() + " - " + index.get());
                        index.set(index.get() + 10);
                    } else {
                        monitor.unlock();
                        return;
                    }
                    monitor.unlock();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
