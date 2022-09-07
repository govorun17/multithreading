package org.example.exercises.sixth;

import java.util.PriorityQueue;
import java.util.Queue;

public class MyQueue {
    private final Queue<Object> queue;
    private boolean valueSet = false;

    public MyQueue() {
        this.queue = new PriorityQueue<>();
    }

    public synchronized Object get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object o = queue.poll();
        if (queue.isEmpty()) {
            valueSet = false;
        }
        notifyAll();
        return o;
    }

    public synchronized void put(Object o) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        valueSet = true;
        queue.add(o);
        notifyAll();
    }
}
