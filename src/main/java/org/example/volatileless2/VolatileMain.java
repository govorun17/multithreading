package org.example.volatileless2;

import org.example.thread.base.ColorConst;

public class VolatileMain {

    private static volatile int counter;

    public static void main(String[] args) {
        new SimpleWriter().start();
        new SimpleReader().start();
    }

    private static class SimpleWriter extends Thread {
        @Override
        public void run() {
            int localCounter = counter;
            for (int i = 0; i < 10; i++) {
                counter = ++localCounter;
                System.out.println(ColorConst.GREEN + "writer inc counter by 1 = " + localCounter);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class SimpleReader extends Thread {
        @Override
        public void run() {

            int localCounter = counter;
            while (localCounter <= 10) {
                if (localCounter != counter) {
                    System.out.println(ColorConst.RED + "Count = " + localCounter);
                    localCounter = counter;
                }
            }

        }
    }
}
