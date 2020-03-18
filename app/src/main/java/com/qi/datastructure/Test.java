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
            singleLinked.add(i + "");
        }
        for (int i = 0; i <= value; i++) {
            singleLinked.add((value - i) + "");
        }
         System.out.println("singleLinked isPalindrome " + singleLinked.isPalindrome());
       /* for (int i = 0; i <= value; i++) {
            singleLinked.addFirst((value - i) + "");
        }*/
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.removeLast();
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.remove(2+"");
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.remove(1+"");
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.removeLast();
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.removeLast();
        System.out.println("singleLinked = " + singleLinked);
    }
}
