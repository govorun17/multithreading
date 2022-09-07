package org.example.exercises.seventh;

import org.example.thread.base.ColorConst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Fork f1 = new Fork();
        Fork f2 = new Fork();
        Fork f3 = new Fork();
        Fork f4 = new Fork();
        Fork f5 = new Fork();
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new Philosopher(f1, f2, ColorConst.WHITE));
        service.submit(new Philosopher(f2, f3, ColorConst.RED));
        service.submit(new Philosopher(f3, f4, ColorConst.GREEN));
        service.submit(new Philosopher(f4, f5, ColorConst.BLUE));
        service.submit(new Philosopher(f5, f1, ColorConst.YELLOW));
        service.shutdown();
    }
}
