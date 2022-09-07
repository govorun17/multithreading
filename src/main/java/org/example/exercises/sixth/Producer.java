package org.example.exercises.sixth;

import java.util.Random;

public class Producer implements Runnable {
    private final MyQueue myQueue;

    public Producer(MyQueue myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (true){
            myQueue.put(new DTO(String.valueOf(r.nextInt())));
        }
    }
}