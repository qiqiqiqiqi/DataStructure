package com.qi.datastructure.practice.thread.printoddeven;

/**
 * 打印1~100的数字，一个线程打印奇数，一个线程打印偶数
 * Created by feng on 2020/3/31.
 */
public class PrintOddEvenTask implements Runnable {
    private  int num = 1;
    public int maxValue;

    public PrintOddEvenTask(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public  void run() {
        synchronized (this) {
            while (num <= maxValue) {
                if (num % 2 == 0) {
                    if (Thread.currentThread().getName().equalsIgnoreCase("even")) {
                        System.out.println(Thread.currentThread().getName() + ":num=" + num);
                        sleep();
                    }
                } else {
                    if (Thread.currentThread().getName().equalsIgnoreCase("odd")) {
                        System.out.println(Thread.currentThread().getName() + ":num=" + num);
                        sleep();
                    }
                }
                num++;
                notifyAllAndWaitCur();
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void notifyAllAndWaitCur() {
        notifyAll();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PrintOddEvenTask printOddEvenTask = new PrintOddEvenTask(100);
        Thread oddThread = new Thread(printOddEvenTask, "odd");
        Thread evenThread = new Thread(printOddEvenTask, "even");
        oddThread.start();
        evenThread.start();
    }
}
