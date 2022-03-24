package com.thread.sync;

/**
 * 内部类实现死锁
 */
public class DeathLock {

    private static Object a = new Object();
    private static Object b = new Object();

    static class ThreadA extends Thread{
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
    static class ThreadB extends Thread{
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

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
    }
}
