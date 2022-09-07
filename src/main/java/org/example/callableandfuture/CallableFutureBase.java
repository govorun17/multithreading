package org.example.callableandfuture;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Timer {
    public Instant start;
    public Instant end;

    public double timeInSec() {
        return Duration.between(start, end).toMillis() / 1000.0;
    }
}

public class CallableFutureBase {

    public static void main(String[] args) {
        List<Future<Double>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            futures.add(executorService.submit(() -> {
                Timer timer = new Timer();
                Random random = new Random();
                timer.start = Instant.now();

                Thread.sleep(random.nextInt(10000));

                timer.end = Instant.now();
                return timer.timeInSec();
            }));
        }

        executorService.shutdown();

        futures.parallelStream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return 0;
                })
                .forEach(r -> System.out.println("Thread execution time: " + r));
    }

}
