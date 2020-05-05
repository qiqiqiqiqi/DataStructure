package com.qi.datastructure.proxy;

import com.qi.datastructure.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by feng on 2020/3/31.
 */
public class TestImpl implements ITest, I2Test {
    @Override
    public void test1() {
        System.out.println("TestImpl--test1()");
    }

    @Override
    public void test2(String test2) {
        System.out.println("TestImpl--test2()");
    }

    @Override
    public void test2(String test1, String test2) {

    }

    @Override
    public void i2Test() {
        System.out.println("TestImpl--i2Test()");
    }

    public void proxy() {

        Object testImpl = Proxy.newProxyInstance(TestImpl.class.getClassLoader(), TestImpl.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                return method.invoke(TestImpl.this, args);
            }
        });
        ((ITest) testImpl).test1();
        ((ITest) testImpl).test2("jjj");
        ((I2Test) testImpl).i2Test();

    }

    public static void main(String[] args) {
        List[] lists=new List[9];
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        new TestImpl().proxy();
    }


}
