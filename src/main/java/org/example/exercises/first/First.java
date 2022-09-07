package org.example.exercises.first;

public class First {
    public static void main(String[] args) throws InterruptedException {
        Thread stateThread = new Thread(new RunnableImpl());
        System.out.println(stateThread.getState().name());
        stateThread.start();
        Thread.sleep(1000);
        System.out.println(stateThread.getState().name());

    }

    private static class RunnableImpl implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getState().name());
        }
    }
}
