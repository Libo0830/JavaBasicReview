package com.lb.use;

public class WaitNotifyUse {

    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();
        Thread t = new Thread(mt);
        t.start();
        Thread.sleep(1000);
        mt.setFlag(false);
        Thread t1 = new Thread(mt);
        t1.start();
    }

}

class MyThread extends Thread {
    private Integer num = 10;
    private boolean flag = true;

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        synchronized (num){
            if (flag){
                this.testWait();
            } else {
                this.testNotify();
            }
        }
    }

    public void testWait(){
        synchronized (num){
            try {
                num = num - 1;
                System.out.println(Thread.currentThread().getName() + "线程wait执行前, num:" + num);
                num.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testNotify(){
        System.out.println(Thread.currentThread().getName() + "线程notify执行前, num:" + num);
        num.notify();
        System.out.println(Thread.currentThread().getName() + "线程notify执行后, num:" + num);
    }
}

class Product {
    private String productName;
    private int productNum;

    public Product(){}

    public Product(String productName, int productNum){
        this.productName = productName;
        this.productNum = productNum;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductNum() {
        return productNum;
    }
}











