package com.basic.use;

/**
 * 死锁示例
 */
public class DeadLock {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        ThreadA a1 = new ThreadA(a, b);
        ThreadB b1 = new ThreadB(a, b);
        a1.start();
        b1.start();
    }

}

class ThreadA extends Thread {
    private Object a;
    private Object b;

    public ThreadA(Object a, Object b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (a){
            System.out.println(Thread.currentThread().getName()+"获取a对象的锁");
            try {
                Thread.sleep(4000);
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+"获取b对象的锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ThreadB extends Thread {
    private Object a;
    private Object b;

    public ThreadB(Object a, Object b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (b){
            try {
                System.out.println(Thread.currentThread().getName()+"获取b对象的锁");
                Thread.sleep(4000);
                synchronized (a){
                    System.out.println(Thread.currentThread().getName()+"获取a对象的锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}