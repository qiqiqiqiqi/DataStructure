package com.qi.datastructure.array;

import java.util.ArrayList;

/**
 * 环形数组队列
 * 1.head 初始值为0，指向队列第一个节点；
 * 2.near初始值为0，指向队列最后一个节点的下一个节点(即队列会预留一个空位)；
 * 3.队列为空时head==near；
 * 4.满队列时（near+1）%maxSize==head;
 * 5.队列中的有效数据长度 （near-head+maxSize）%maxSize;
 * <p>
 * Created by feng on 2020/3/23.
 */
public class CircleArrayQueue<T> {
    int maxSize;
    int head, near;
    private Node<T>[] arry;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arry = new Node[maxSize];
    }

    public boolean isEmpty() {
        return head == near;
    }

    public boolean isFull() {
        return (near + 1) % maxSize == head;
    }

    public boolean add(T data) {
        if (!isFull()) {
            Node<T> node = new Node<T>(data);
            arry[near] = node;
            near = (near + 1) % maxSize;
            return true;
        }
        return false;
    }

    public Node<T> get() {
        if (!isEmpty()) {
            Node<T> node = arry[head];
            head = (head + 1) % maxSize;
            return node;
        }
        return null;
    }

    public int size() {
        return (near - head + maxSize) % maxSize;
    }

    @Override
    public String toString() {
        String content = "";
        for (int i = head; i < (head + size()); i++) {
            T data = arry[i % maxSize].data;
            if (i < (head + size() - 1)) {
                content += data.toString() + ",";
            } else {
                content += data.toString();
            }
        }
        return "CircleArrayQueue{" + content + "}";
    }
}
