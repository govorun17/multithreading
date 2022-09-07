package org.example.locks;

import org.example.thread.base.ColorConst;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBuffer {

    private static final Lock monitor = new ReentrantLock(true);

    private static final Condition canRead = monitor.newCondition();
    private static final Condition canWrite = monitor.newCondition();

    private static int buffer = 0;
    private static boolean isEmpty = true;


    public static void main(String[] args) {

        new Thread(SynchronizedBuffer::blockingWrite).start();
        new Thread(SynchronizedBuffer::blockingRead).start();

    }

    private static void blockingWrite() {
        for (int i = 0; i < 10; i++) {
            monitor.lock();

            try {
                while (!isEmpty) {
                    canWrite.await(1, TimeUnit.SECONDS);
                }
                buffer++;
                isEmpty = false;
                System.out.println(ColorConst.RED + "Produced: " + buffer);
                canRead.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.unlock();
            }

        }
    }

    private static void blockingRead() {
        for (int i = 0; i < 10; i++) {
            monitor.lock();

            try {
                while (isEmpty) {
                    canWrite.await(1, TimeUnit.SECONDS);
                }
                int readVal = buffer;
                isEmpty = true;
                System.out.println(ColorConst.GREEN + "Read value: " + readVal);
                canWrite.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.unlock();
            }
        }
    }
}
