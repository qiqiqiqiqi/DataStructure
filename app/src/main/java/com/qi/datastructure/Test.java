package com.qi.datastructure;

import com.qi.datastructure.linked.Node;
import com.qi.datastructure.linked.singlelinked.SingleLinked;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // test();
        testSingleLinked();
    }

    public static void test() {

        int a = 12;
        int b = 4;
        System.out.print("7>>>3=" + (7 >>> 3) + ",a%b=" + (a % b) + ",a&(b-1)=" + (a & (b - 1)));
    }

    public static void testSingleLinked() {
        int value = 3;
        SingleLinked<String> singleLinked = new SingleLinked<>();
        for (int i = 0; i < value; i++) {
            Node<String> node = new Node<>(i + "");
            singleLinked.add(node);
        }
        for (int i = 0; i <= value; i++) {
            Node<String> node = new Node<>((value - i) + "");
            singleLinked.add(node);
        }
        //list.get(list.size()-1).next=new Node<>(0 + "");

        System.out.println("singleLinked isPalindrome " + singleLinked.isPalindrome());
    }
}
