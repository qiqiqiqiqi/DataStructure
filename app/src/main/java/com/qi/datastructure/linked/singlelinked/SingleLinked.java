package com.qi.datastructure.linked.singlelinked;

import com.qi.datastructure.linked.Node;


public class SingleLinked<T> {

    public Node<T> root;

    public SingleLinked() {
    }

    public SingleLinked(Node<T> root) {
        this.root = root;
    }

    public boolean isPalindrome() {
        return isPalindrome(root);
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
        Node firstNode = null;
        while (currentNode != null) {
            Node next = currentNode.next;
            currentNode.next = firstNode;
            firstNode = currentNode;
            currentNode = next;
        }
        return firstNode;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node currentNode = this.root;
        while (currentNode != null) {
            stringBuilder.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        return stringBuilder.toString();
    }
}
