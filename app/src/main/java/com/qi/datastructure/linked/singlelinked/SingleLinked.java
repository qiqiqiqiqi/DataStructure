package com.qi.datastructure.linked.singlelinked;

import com.qi.datastructure.linked.Node;


public class SingleLinked<T> {

    private Node<T> head;//哨兵节点

    public SingleLinked() {
        head = new Node<>();
    }


    public SingleLinked add(T value) {
        addLast(value);
        return this;
    }

    public SingleLinked addFirst(T value) {
        head.next = new Node<>(value, head.next);
        return this;
    }

    public SingleLinked addLast(T value) {
        Node<T> p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node<>(value, null);
        return this;
    }

    public SingleLinked removeLast() {
        Node<T> p = head;
        if (head.next != null) {
            while (p.next != null && p.next.next != null) {
                p = p.next;
            }
            p.next = p.next.next;
        }
        return this;
    }

    public SingleLinked remove(T value) {
        Node<T> node = new Node<>(value);
        if (head != null) {
            if (head == node || head.equals(node)) {
                node.next = head.next;
            }
            Node<T> currentNode = head;
            while (currentNode != null) {
                if (currentNode.next == node || currentNode.next.equals(node)) {
                    currentNode.next = currentNode.next.next;
                    break;
                }
                currentNode = currentNode.next;
            }
        }
        return this;
    }


    public boolean isPalindrome() {
        return isPalindrome(head.next);
    }

    /**
     * 0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0
     * 链表的长度为偶数时slow，为奇数时slow指向中点
     *
     * @param node
     * @return
     */
    public boolean isPalindrome(Node<T> node) {
        System.out.println(this);
        if (node == null) {
            return false;
        }
        if (node.next == null) {
            return true;
        }
        Node<T> slow = node, fast = node;
        //1.双指针找到链表的中点 slow
        while (slow.next != null && (fast.next != null && fast.next.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //2.反转后部分(不论链表的长度时奇数还是偶数，后部分的开始node都是slow的下个node)
        slow.next = reserve(slow.next);
        System.out.println(this);
        //3.比较前后部分
        Node<T> hNode = slow.next, lNode = node;
        while (hNode != null) {
            if (!(lNode.data == hNode.data || lNode.data.equals(hNode.data))) {
                return false;
            }
            hNode = hNode.next;
            if (hNode != null) {
                lNode = lNode.next;
            }
        }
        //4.还原链表
        //当链表的长度为奇数时,比较完后lNode指向链表的中点，还原时需要从lNode.next开始
        if (lNode == slow) {
            lNode.next = reserve(lNode.next);
        } else {
            lNode.next.next = reserve(lNode.next.next);
        }

        //当链表的长度为偶数数时,比较完后lNode指向链表的后部分的开始点，直接从lNode开始

        System.out.println(this);
        return true;


    }

    /**
     * 链表反转
     *
     * @param currentNode 链头
     * @return
     */
    private Node<T> reserve(Node<T> currentNode) {
        Node<T> firstNode = null;
        while (currentNode != null) {
            Node<T> next = currentNode.next;
            currentNode.next = firstNode;
            firstNode = currentNode;
            currentNode = next;
        }
        return firstNode;
    }

    /**
     * 整条链表反转
     *
     * @return
     */
    private Node<T> reserve() {
        return reserve(head.next);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node currentNode = this.head.next;
        while (currentNode != null) {
            stringBuilder.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        return stringBuilder.toString();
    }
}
