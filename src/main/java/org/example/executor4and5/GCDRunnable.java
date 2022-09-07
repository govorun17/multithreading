package org.example.executor4and5;

import org.example.thread.base.ColorConst;

import java.util.Random;

public class GCDRunnable extends Random implements Runnable {

    private boolean isDaemon = false;

    public GCDRunnable(boolean isDaemon) {
        this.isDaemon = isDaemon;
    }

    public GCDRunnable() {
    }

    @Override
    public void run() {
        String daemonDesc = this.isDaemon ? "daemon " : "user ";
        String threadDescription = daemonDesc + Thread.currentThread().getName();

        System.out.println(ColorConst.RED + "Start " + threadDescription);
        for (int i = 0; i < 10000000; i++) {
            int a = nextInt();
            int b = nextInt();

            if (i % 10000 == 0) {
                if (!Thread.interrupted()) {
                    int gcd = computeGCD(a, b);
                    if (gcd > 5) {
                        System.out.println(ColorConst.GREEN + "Running in " + threadDescription + ". GCD of " + a + " and " + b + " is " + gcd);
                    }
                } else {
                    System.out.println(ColorConst.RED + "Thread was interrupted");
                    return;
                }
            }
        }

        System.out.println(ColorConst.RED + "End " + threadDescription);

    }

    private int computeGCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return computeGCD(b, a%b);
        }
    }


}
