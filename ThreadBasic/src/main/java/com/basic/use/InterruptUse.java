package com.basic.use;

public class InterruptUse {

    public static void main(String[] args) throws InterruptedException {
        InterruptThread interruptT = new InterruptThread("中断使用");
        interruptT.start();
        Thread.sleep(10);
        interruptT.interrupt();
        System.out.println(interruptT.isInterrupted());
    }

}
/**
 * 继承Thread类
 */
class InterruptThread extends Thread {
    private String threadName;
    public InterruptThread(String threadName){
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            System.out.println(threadName + "<---->" +Thread.currentThread().getName() + "<---->" + i);
            if (this.interrupted()) {
                break;
            }
        }
    }
}