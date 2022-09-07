package org.example.exercises.seventh;

import java.util.Random;

public class Philosopher extends Random implements Runnable{
    private final Fork leftFork;
    private final Fork rightFork;

    private boolean leftIsMy = false;
    private boolean rightIsMy = false;

    private final String color;

    public Philosopher(Fork leftFork, Fork rightFork, String color) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            try {
                pickUpLeftFork();
                pickUpRightFork();
                tryToEat();
                putDownLeftFork();
                putDownRightFork();
                philosophising();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void putDownRightFork() {
        if (rightIsMy) {
            rightFork.putDown();
            rightIsMy = false;
        }
        notifyAll();
    }

    private synchronized void putDownLeftFork() {
        if (leftIsMy) {
            leftFork.putDown();
            leftIsMy = false;
        }
        notifyAll();
    }

    private void tryToEat() throws InterruptedException {
        if (rightIsMy && leftIsMy) {
            System.out.println(color + Thread.currentThread().getName() + " - " + "is eating");
            Thread.sleep(nextInt(5000));
        }
    }

    private void philosophising() throws InterruptedException {
        System.out.println(color + Thread.currentThread().getName() + " - " + "is philosophising");
        Thread.sleep(nextInt(10000));
    }

    private synchronized void pickUpRightFork() throws InterruptedException {
        if (!rightIsMy) {
            if (rightFork.isUp()) {
                wait(nextInt(1000));
            }
            if (!rightFork.isUp()) {
                rightFork.pickUp();
                rightIsMy = true;
            }
        }
    }

    private synchronized void pickUpLeftFork() throws InterruptedException {
        if (!leftIsMy) {
            if (leftFork.isUp()) {
                wait(nextInt(1000));
            }
            if (!leftFork.isUp()) {
                leftFork.pickUp();
                leftIsMy = true;
            }
        }
    }
}
