package com.qi.datastructure.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by feng on 2020/4/22.
 */
public class VolatileTest {
    public boolean flag;
    public  int value;

    private VolatileTest(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {
        final VolatileTest volatileTest = new VolatileTest(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!volatileTest.flag) {
//                    volatileTest.value+=1;
//                   System.out.println("thread b run,vlaue=" + volatileTest.flag);

                }
                System.out.println("thread b end");
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileTest.flag = true;
                System.out.println("thread a end ");
            }
        }).start();
    }
}
