package com.thread.sync;

public class SynchronizedUse {

    public static void main(String[] args) throws InterruptedException {
        SyncThread syncThread1 = new SyncThread("A");
        SyncThread syncThread2 = new SyncThread("B");
        syncThread1.start();
        syncThread2.start();
    }

}

/**
 * 继承Thread类
 */
class SyncThread extends Thread {
    private String threadName;
    public SyncThread(String threadName){
        this.threadName = threadName;
    }

    @Override
    public void run() {
//        onMethod();
        inMethod();
    }

    /**
     * synchronized 方法上
     */
    public synchronized void onMethod(){
        for (int i=0; i<10; i++){
            System.out.println(threadName + "<---->" +Thread.currentThread().getName() + "<---->" + i);
        }
    }

    public void inMethod(){
        synchronized (this){
            for (int i=0; i<10; i++){
                System.out.println(threadName + "<---->" +Thread.currentThread().getName() + "<---->" + i);
            }
        }
    }
}