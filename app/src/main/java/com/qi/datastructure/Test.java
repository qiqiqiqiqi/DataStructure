package com.qi.datastructure;

import com.qi.datastructure.array.CircleArrayQueue;
import com.qi.datastructure.array.SpareArray;
import com.qi.datastructure.linked.Node;
import com.qi.datastructure.linked.singlelinked.SingleLinked;

import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        // test();
        //testSingleLinked();
        //testSpareArray();
        testCircleArrayQueue();
    }

    public static void test() {

        int a = 12;
        int b = 4;
        System.out.print("7>>>3=" + (7 >>> 3) + ",a%b=" + (a % b) + ",a&(b-1)=" + (a & (b - 1)));
    }

    public static void testSingleLinked() {
        int value = 3;
        SingleLinked<String> singleLinked = new SingleLinked<>();
        Node<String> circleNode = null;
        for (int i = 0; i < value; i++) {
            singleLinked.add(i + "");
            if (i == value - 1) {
                circleNode = singleLinked.findByValue(i + "");
            }
        }
        for (int i = 0; i <= value; i++) {
            singleLinked.add((value - i) + "");
        }
        //chech is Palindrome
        System.out.println("singleLinked isPalindrome " + singleLinked.isPalindrome());
        //chechCircle
       /* singleLinked.findByValue(value + "").next = circleNode;
        System.out.println("singleLinked checkCircle " + singleLinked.checkCircle());*/
       /* System.out.println("singleLinked = " + singleLinked);
        singleLinked.removeLast();
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.remove(2+"");
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.remove(1+"");
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.removeLast();
        System.out.println("singleLinked = " + singleLinked);
        singleLinked.removeLast();
        System.out.println("singleLinked = " + singleLinked);*/
        //合并两个有序链表
        SingleLinked<Integer> singleLinked2 = new SingleLinked<>();
        Node<Integer> node1 = new Node<Integer>(3, new Node<Integer>(7, new Node<Integer>(31, new Node<Integer>(45, null))));
        Node<Integer> node2 = new Node<Integer>(3, new Node<Integer>(5, new Node<Integer>(6, new Node<Integer>(45, null))));
        Node<Integer> sortedLists = singleLinked2.mergeSortedLists(node1, node2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        singleLinked.removeLastIndex(3);


    }

    public static void testSpareArray() {
        SpareArray spareArray = new SpareArray();

        int[][] integers = new int[11][11];
        integers[1][3] = 1;
        integers[2][5] = 7;
        integers[3][4] = 9;
        int[][] ints = spareArray.toSpareArray(integers);
        int[][] sources = spareArray.toSourceArray(ints);
    }

    public static void testCircleArrayQueue(){
        CircleArrayQueue<Integer> integerCircleArrayQueue = new CircleArrayQueue<>(4);
        integerCircleArrayQueue.add(1);
        integerCircleArrayQueue.add(2);
        integerCircleArrayQueue.add(3);
        System.out.println(integerCircleArrayQueue);
        integerCircleArrayQueue.get();
        System.out.println(integerCircleArrayQueue);
        integerCircleArrayQueue.get();
        System.out.println(integerCircleArrayQueue);
        integerCircleArrayQueue.add(4);
        System.out.println(integerCircleArrayQueue);
    }
}
