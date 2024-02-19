package com.aa.vthreads;

public class VthreadsSimple {

    public void regularThreads() throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            new Thread(() -> System.out.println("Task ID : " + finalI + " is run in thread : " + Thread.currentThread().getName())).start();
        }
        Thread.sleep(5000);
    }

    public void virtualThreads() throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            Thread.startVirtualThread(() -> System.out.println("Task ID : " + finalI + " is run in thread : " + Thread.currentThread().getName()));
        }
        Thread.sleep(5000);
    }

}
