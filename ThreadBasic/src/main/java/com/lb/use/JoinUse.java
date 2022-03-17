package com.lb.use;

/**
 * 任何地方当调用了t.join()，就必须要等待线程t执行完毕后，才能继续执行其他线程。
 * 这里其实是运用了Java中最顶级对象Object提供的方法wait()。
 * wait(）方法用于线程间通信，它的含义是通知一个线程等待一下，让出CPU资源，注意这里是会放弃已经占有的资源的。
 * 直到t线程执行完毕，再调用notify()唤醒当前正在运行的线程。
 */
public class JoinUse {

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1("A");
        Thread1 t2 = new Thread1("B");
        t1.start();
        t1.join();
        t2.start();
    }
}

/**
 * 继承Thread类
 */
class Thread1 extends Thread {
    private String threadName;
    public Thread1(String threadName){
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "<---->" +Thread.currentThread().getName() + "<---->" + i);
        }
    }
}