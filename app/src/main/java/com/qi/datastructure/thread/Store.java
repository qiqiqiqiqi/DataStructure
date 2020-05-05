package com.qi.datastructure.thread;

/**
 * Created by feng on 2020/3/31.
 */
public class Store implements Runnable {
    public static final int maxCount = 4;
    public int count;

    @Override
    public void run() {
        while (true) {
            if (count > 0) {
                consume();
            } else {
                produce();
            }
        }
    }

    public void produce() {
        synchronized (this) {
            while (this.count < Store.maxCount) {
                this.count++;
                System.out.println(Thread.currentThread().getName() + ":" + this.count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.notifyAll();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + ":" + this.count);
            this.count--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notifyAll();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Store store = new Store();
        new Thread(store, "生产者").start();
        new Thread(store, "消费者").start();

    }


}
