package com.thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUse {
    public static void main(String[] args) {
        LockThread t1 = new LockThread("A");
        LockThread t2 = new LockThread("B");
        t1.start();
        t2.start();
    }
}

class LockThread extends Thread {
    private Lock lock = new ReentrantLock();
    private String threadName;
    public LockThread(String threadName){
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(threadName+"   "+ Thread.currentThread().getName()+"获取锁");
        lock.lock();
        for (int i=0; i<50; i++){
            System.out.println(threadName+"   "+ Thread.currentThread().getName()+"    "+i + "   锁状态："+ lock.tryLock());
            if (i==2){
                System.out.println(threadName+"   "+ Thread.currentThread().getName()+"释放锁");
                lock.unlock();
            }
        }
    }
}
