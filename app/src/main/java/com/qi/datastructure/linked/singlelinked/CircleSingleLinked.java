package com.qi.datastructure.linked.singlelinked;

import com.qi.datastructure.linked.Node;

/**
 * N个人围成一圈，从第一个人开始报数，报到m的人出圈，剩下的人继续从1开始报数，
 * 报到m的人出圈；如此往复，直到所有人出圈。（模拟此过程，输出出圈的人的序号）
 * Created by feng on 2020/3/29.
 */


public class CircleSingleLinked {

    public static void main(String[] args) {
        CircleSingleLinked circleSingleLinked = new CircleSingleLinked();
        circleSingleLinked.createJosephCircle(50, 2, 2);
    }

    public CircleSingleLinked() {
    }

    /**
     * @param maxSize  围成一圈人的总数
     * @param start    第N个人开始报数
     * @param intervel 报到m的人出圈
     */
    public void createJosephCircle(int maxSize, int start, int intervel) {
        if (maxSize < 1) {
            return;
        }
        //生成一个环
        Node<Integer> first = null, end = null;
        for (int i = 0; i < maxSize; i++) {
            if (i == 0) {
                first = new Node<>(i + 1);
                end = first;
            } else {
                end.next = new Node<Integer>(i + 1);
                end.next.next = first;
                end = end.next;
            }
        }
        //跳到开始位置
        for (int i = 0; i < start - 1; i++) {
            first = first.next;
            end = end.next;
        }
        // 循环报数
        while (true) {
            if (first == end) {
                //出圈
                System.out.print(first.data);
                break;
            } else {
                //跳到报到m的人的位置
                for (int i = 0; i < intervel - 1; i++) {
                    first = first.next;
                    end = end.next;
                }
                System.out.print(first.data + ",");
                end.next = first.next;
                first = first.next;
            }
        }
    }
}
