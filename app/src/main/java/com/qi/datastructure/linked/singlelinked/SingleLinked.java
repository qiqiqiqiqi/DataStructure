package com.qi.datastructure.linked.singlelinked;


import com.qi.datastructure.linked.Node;

import java.util.Comparator;

public class SingleLinked<T> {

    private Node<T> head;

    public SingleLinked() {
    }


    public void addFirst(T value) {
        Node<T> sentinel = new Node<>(null, head);
        sentinel.next = new Node<>(value, sentinel.next);

    }

    public boolean add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> p = new Node<>(null, head);
            while (p.next != null) {
                p = p.next;
            }
            p.next = new Node<>(value, null);
        }
        return true;
    }


    public boolean removeLast() {
        Node<T> sentinel = new Node<>(null, head);
        Node<T> currentNode = head, p = sentinel;
        while (currentNode != null && currentNode.next != null) {
            p = currentNode;
            currentNode = currentNode.next;
        }
        if (p.next != null) {
            p.next = p.next.next;
            return true;
        }
        return false;

    }

    public boolean remove(T value) {
        Node<T> sentinel = new Node<>(null, head);
        Node<T> currentNode = head, p = sentinel;
        while (currentNode != null) {
            if ((currentNode.data == value || currentNode.data.equals(value))) {
                break;
            }
            p = currentNode;
            currentNode = currentNode.next;
        }
        if (p.next != null) {
            p.next = p.next.next;
            return true;
        }
        return false;
    }

    /**
     * 删除倒数第k个节点
     * @param lastIndex
     * @return
     */
    public boolean removeLastIndex(int lastIndex) {
        if (lastIndex > 0) {
            Node<T> sentinel = new Node<>(null, head);
            Node<T> slow = head, fast = head;
            int i = 0;
            while (fast != null && i < lastIndex ) {
                fast = fast.next;
                i++;
            }
            if (fast != null) {
                Node<T> p = sentinel;
                while (fast != null) {
                    fast = fast.next;
                    p = slow;
                    slow = slow.next;
                }
                p.next = p.next.next;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        Node currentNode = head;
        while (currentNode != null) {
            stringBuilder.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        System.out.println("removeLastIndex():" + stringBuilder.toString());
        return false;
    }

    public Node<T> findByValue(T value) {
        if (value == null) {
            return null;
        }
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (value == currentNode.data || value.equals(currentNode.data)) {
                break;
            }
            currentNode = currentNode.next;
        }
        return currentNode;
    }


    public boolean isPalindrome() {
        return isPalindrome(head);
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
        while (fast != null && fast.next != null/*slow.next != null && (fast.next != null && fast.next.next != null)*/) {
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
     * @param currentNode 反转的开始节点
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
    public Node<T> reserve() {
        return reserve(head);
    }

    /**
     * 快慢指针遍历链表，如果快慢指针会重叠说明有环（）
     *
     * @return
     */
    public boolean checkCircle() {
        Node<T> node = head;
        if (node != null) {
            Node<T> slow = node, fast = node;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 合并两个有序链表
     *
     * @param n1
     * @param n2
     * @param comparator
     * @return
     */
    public Node<T> mergeSortedLists(Node<T> n1, Node<T> n2, Comparator<? super T> comparator) {
        Node<T> p = new Node<>(null, null);
        Node<T> firstNode = p;
        while (n1 != null && n2 != null) {
            int compare = comparator.compare(n1.data, n2.data);
            if (compare < 0) {
                p.next = n1;
                n1 = n1.next;
            } else {
                p.next = n2;
                n2 = n2.next;
            }
            p = p.next;
        }
        if (n1 != null) {
            p.next = n1;
        } else {
            p.next = n2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Node currentNode = firstNode.next;
        while (currentNode != null) {
            stringBuilder.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        System.out.println("mergeSortedLists():" + stringBuilder.toString());
        return firstNode.next;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node currentNode = this.head;
        if (!checkCircle()) {
            while (currentNode != null) {
                stringBuilder.append(currentNode.data).append(", ");
                currentNode = currentNode.next;
            }
        }
        return stringBuilder.toString();
    }
}
