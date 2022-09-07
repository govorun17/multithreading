package org.example.thread.base;

public class Main {
    public static void main(String[] args) {

        SimpleTread th1 = new SimpleTread();
        th1.start();

        SimpleTread th2 = new SimpleTread();
        th2.start();
        th2.interrupt();

        Thread th3 = new Thread(new SimpleRunner());
        th3.start();

        new Thread(() -> System.out.println("Hello from lambda")).start();

        System.out.println("Hello!");
    }
}

class SimpleTread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(ColorConst.RED + "EXCEPTION - " + currentThread().getName() + " was interrupted");
                return;
            }
            System.out.println(ColorConst.GREEN + "INFO - " + currentThread().getName() + " - " + i);
        }
    }
}

class SimpleRunner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(ColorConst.RED + "EXCEPTION - runnable - " + Thread.currentThread().getName() + " was interrupted");
            }
            System.out.println(ColorConst.GREEN + "INFO - runnable - " + Thread.currentThread().getName() + " - " + i);
        }
    }
}