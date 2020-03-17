package com.qi.datastructure;

import com.qi.datastructure.linked.Node;
import com.qi.datastructure.linked.singlelinked.SingleLinked;

import java.util.ArrayList;
import java.util.Hashtable;

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
        int value = 10;
        ArrayList<Node<String>> list = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            Node<String> node = new Node<>(i + "");
            if (!list.isEmpty()) {
                list.get(list.size() - 1).next = node;
            }
            list.add(node);
        }
        for (int i =0; i <= value; i++) {
            Node<String> node = new Node<>((value - i) + "");
            if (!list.isEmpty()) {
                list.get(list.size() - 1).next = node;
            }
            list.add(node);
        }

        SingleLinked<String> singleLinked = new SingleLinked<>(list.get(0));
        System.out.println("singleLinked isPalindrome " + singleLinked.isPalindrome());
    }
}
