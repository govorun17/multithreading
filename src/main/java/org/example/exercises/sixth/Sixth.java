package org.example.exercises.sixth;

public class Sixth {
    public static void main(String[] args) throws InterruptedException {
        MyQueue queue = new MyQueue();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}
