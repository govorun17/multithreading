package org.example.locks;

import org.example.thread.base.ColorConst;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class WaitingQueue {

    public static void main(String[] args) throws InterruptedException {
        int operators = 5;
        int customers = 21;

        SemaphoredServiceDesk serviceDesk = new SemaphoredServiceDesk(operators, customers);
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, customers)
                .forEach(client -> executorService.submit(() -> {
                    serviceDesk.connect();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(ColorConst.GREEN + "number of connected customers: " + serviceDesk.getCustomersConnected());
                    System.out.println(ColorConst.RED + "number of customers in queue: " + serviceDesk.getCustomersWaiting());

                }));
        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }

    private static class SemaphoredServiceDesk {

        private AtomicInteger connectedCustomers = new AtomicInteger();
        private AtomicInteger customersQueued;

        private Semaphore semaphore;

        public SemaphoredServiceDesk(int operators, int customers) {
            semaphore = new Semaphore(operators);
            customersQueued = new AtomicInteger(customers);

        }

        public void connect() {
            Random random = new Random();
            try {
                semaphore.acquire();
                connectedCustomers.incrementAndGet();
                customersQueued.decrementAndGet();
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }

        }

        private void disconnect() {
            semaphore.release();
            connectedCustomers.decrementAndGet();
        }

        private int getCustomersConnected() {
            return connectedCustomers.get();
        }

        private int getCustomersWaiting() {
            return customersQueued.get();
        }
    }
}
