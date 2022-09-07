package org.example.synchronys3;

import java.util.ArrayList;
import java.util.List;

public class SyncBlocks {

    private int[] a = {0,1,2,3,4,5,6,7,8,9,10};
    private int[] b = {0,11,12,13,14,15,16,17,18,19,20};
    private List<Integer> intList1 = new ArrayList<>();
    private List<Integer> intList2 = new ArrayList<>();

    private final Object l1 = new Object();
    private final Object l2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        SyncBlocks sb = new SyncBlocks();
        sb.copy();

    }

    private void copy() throws InterruptedException {

        long start = System.currentTimeMillis();

        Thread first = new Thread(new RunnerA());
        Thread second = new Thread(new RunnerB());

        first.start();
        second.start();

        long end = System.currentTimeMillis();

        first.join();
        second.join();

        System.out.println("Time: " + (end - start));

    }

    private void copyArrayA() {
        synchronized (l1) {
            for (int i = 0; i < a.length; i++) {
                intList1.add(a[i]);
                System.out.println(intList1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void copyArrayB() {
        synchronized (l2) {
            for (int i = 0; i < b.length; i++) {
                intList2.add(b[i]);
                System.out.println(intList2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class RunnerA implements Runnable{
        @Override
        public void run() {

            copyArrayA();

        }
    }

    private class RunnerB implements Runnable{
        @Override
        public void run() {

            copyArrayB();

        }
    }
}
