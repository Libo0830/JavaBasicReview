package com.lb.use;

/**
 * 对Product的数量字段使用 volatile 简单实现生产者消费者模式
 */
public class MethodUse {

    public static void main(String[] args) {
        Product product = new Product("冰墩墩儿", 10);
        Thread t1 = new Thread(new Factory(product));
        Thread t2 = new Thread(new Customer(product));
        t1.start();
        t2.start();
    }
}

class Factory extends Thread{
    private Product product;

    public Factory(Product product){
        this.product = product;
    }
    @Override
    public void run() {
        int num = product.getProductNum();
        while (num >= 1 && num <20){
            if (num == 15){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num = product.getProductNum();
            num = num + 1;
            System.out.println(Thread.currentThread().getName() + " 商品总数量："+num);
            product.setProductNum(num);
        }
    }
}
class Customer extends Thread {
    private Product product;

    public Customer(Product product){
        this.product = product;
    }

    @Override
    public void run() {
        int num = product.getProductNum();
        while (num >0){
            if (num == 1){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num = product.getProductNum();
            num = num - 1;
            System.out.println(Thread.currentThread().getName() + " 商品剩余数量："+num);
            product.setProductNum(num);
        }
    }
}