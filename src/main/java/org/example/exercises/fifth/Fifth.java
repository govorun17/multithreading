package org.example.exercises.fifth;


public class Fifth {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("a");

        new MyThread(stringBuilder).start();
        new MyThread(stringBuilder).start();
        new MyThread(stringBuilder).start();

    }

    private static class MyThread extends Thread {

        private final StringBuilder stringBuilder;

        public MyThread(StringBuilder stringBuilder) {
            this.stringBuilder = stringBuilder;
        }

        @Override
        public void run() {
            synchronized (stringBuilder) {
                for(int i = 0; i < 100; i++) {
                    if (i % 20 == 0) {
                        System.out.println(Thread.currentThread().getName() + " - " + stringBuilder);
                    }
                }
                char c = stringBuilder.charAt(0);
                c++;
                stringBuilder.deleteCharAt(0);
                stringBuilder.append(c);
            }
        }
    }
}
