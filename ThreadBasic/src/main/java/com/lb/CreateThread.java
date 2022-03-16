package com.lb;

import java.util.concurrent.*;

public class CreateThread {
    public static void main(String[] args) throws InterruptedException {
        /** 继承Thread类 */
        Thread thread1 = new Thread1("继承Thread类实现线程");
        thread1.start();
        /** 实现Runnable接口 */
        Thread thread2 = new Thread(new Thread2("实现Runnable接口创建线程"));
        thread2.start();
        /** 使用Callable和Future接口创建 */
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(callable);
        for (int i=0; i<1; i++){
            System.out.println(Thread.currentThread().getName() + "  " + i);
            Thread thread = new Thread(ft);
            thread.start();
        }
        System.out.println("主线程for循环执行完毕");
        try {
            int sum = ft.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        /** 通过线程池创建线程 */
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i<10;i++){
            Thread2 thread = new Thread2("实现Runnable接口通过线程池创建线程名称"+i);
            executorService.execute(thread);
        }
        //关闭线程池
        executorService.shutdown();
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
        System.out.println(threadName + "----" +Thread.currentThread().getName());
    }
}

/**
 * 实现Runnable接口
 */
class Thread2 implements Runnable{
    private String threadName;
    public Thread2(String threadName){
        this.threadName = threadName;
    }
    @Override
    public void run() {
        System.out.println(threadName + "----" + Thread.currentThread().getName());
    }
}

/**
 * 使用Callable和Future接口创建线程。
 * 　　a:创建Callable接口的实现类 ，并实现Call方法
 * 　　b:创建Callable实现类的实现，使用FutureTask类包装Callable对象，该FutureTask对象封装了Callable对象的Call方法的返回值
 * 　　c:使用FutureTask对象作为Thread对象的target创建并启动线程
 * 　　d:调用FutureTask对象的get()来获取子线程执行结束的返回值
 */
class MyCallable implements Callable<Integer> {

    private int i = 0;

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (; i<5; i++){
            System.out.println(Thread.currentThread().getName()+"   "+ i);
            sum += i;
        }
        return sum;
    }
}