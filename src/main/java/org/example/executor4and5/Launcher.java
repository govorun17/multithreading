package org.example.executor4and5;

import org.example.thread.base.ColorConst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Launcher {
    public static final int POOL_SIZE = 2;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(ColorConst.RED + "Main start");

        boolean isDaemon = true;

        GCDRunnable gcdRunnable = new GCDRunnable(isDaemon);
        //runInOneThread(gcdRunnable, isDaemon);
        runWithExecutors(gcdRunnable, isDaemon);

        System.out.println(ColorConst.RED + "Main end");
    }

    private static void runInOneThread(Runnable r, boolean isDaemon) throws InterruptedException {

        Thread th1 = new Thread(r);
        th1.setDaemon(isDaemon);
        th1.start();
//        Thread.sleep(100);
//        th1.interrupt();

    }

    private static void runWithExecutors(Runnable r, boolean isDaemon) throws InterruptedException {

        ThreadFactory threadFactory = r1 -> {
            Thread th = new Thread(r1);
            th.setDaemon(isDaemon);
            return th;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE, threadFactory);
        for (int i = 0; i < 5; i++) {
            executorService.execute(r);
        }
        //больше заданий не будет
        executorService.shutdown();
        //блокирование завершение потока пока не будет выполнено или на время
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }
}
